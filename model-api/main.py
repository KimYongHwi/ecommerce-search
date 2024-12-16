import ast
import os
from concurrent.futures import ThreadPoolExecutor
from io import BytesIO

import requests
import torch
from PIL import Image
from accelerate import Accelerator
from fastapi import FastAPI
from laser_encoders import LaserEncoderPipeline
from requests.adapters import HTTPAdapter, Retry
from sentence_transformers import SentenceTransformer
from transformers import AutoModel, AutoTokenizer, CLIPProcessor, CLIPModel

from request import GetAllFeatures, GetTextsFeatures, GetImagesFeatures

accelerator = Accelerator()
device = accelerator.device

clip_model_id = 'patrickjohncyh/fashion-clip' # 512
e5_model_id = 'intfloat/multilingual-e5-base' # 768
distiluse_model_id = 'sentence-transformers/distiluse-base-multilingual-cased-v1' # 512

clip_processor = CLIPProcessor.from_pretrained(clip_model_id)
clip_model = CLIPModel.from_pretrained(clip_model_id).to(device)

laser_encoder = LaserEncoderPipeline(lang="eng_Latn") # 1024
distiluse_text_encoder = SentenceTransformer(distiluse_model_id)

e5_tokenizer = AutoTokenizer.from_pretrained(e5_model_id)
e5_model = AutoModel.from_pretrained(e5_model_id).to(device)
e5_model.eval()

app = FastAPI()

headers = {'User-Agent': "Mozilla/5.0 (X11; Linux i686; rv:64.0) Gecko/20100101 Firefox/64.0"},
session = requests.Session()
session.headers = headers

retries = Retry(
    total=10,
    backoff_factor=0.1,
    status_forcelist=[400, 500, 502, 503, 504],
)

session.mount('http://', HTTPAdapter(max_retries=retries, pool_connections=30))


def get_image(url):
    return session.get(url, stream=True).raw


def get_image_raws(urls):
    with ThreadPoolExecutor(max_workers=16) as executor:
        return list(executor.map(get_image, urls))


def embed(docs: list[str]) -> list[list[float]]:
    docs = [f"passage: {d}" for d in docs]
    tokens = e5_tokenizer(docs, padding=True, max_length=512, truncation=True, return_tensors="pt").to(device)

    with torch.no_grad():
        out = e5_model(**tokens)
        last_hidden = out.last_hidden_state.masked_fill(
            ~tokens["attention_mask"][..., None].bool(), 0.0
        )
        doc_embeds = last_hidden.sum(dim=1) / \
                     tokens["attention_mask"].sum(dim=1)[..., None]

    return doc_embeds.cpu().numpy()


@app.post("/features")
async def get_features(param: GetAllFeatures):
    images = [Image.open(os.path.join('./data/images', f"{item_id}.jpg")).convert("RGB") for item_id in param.item_ids]
    inputs = clip_processor(text=param.texts, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_features = outputs.image_embeds.detach().cpu().numpy().tolist()
    laser_all_keywords_text_features = laser_encoder.encode_sentences(param.texts)
    laser_item_names_text_features = laser_encoder.encode_sentences(param.item_names)
    distiluse_all_keywords_text_features = distiluse_text_encoder.encode(param.texts)
    distiluse_item_names_text_features = distiluse_text_encoder.encode(param.item_names)
    e5_all_keywords_text_features = embed(param.texts)
    e5_item_names_text_features = embed(param.item_names)

    return [
        {
            'item_id': item_id,
            'image_features': image_features[idx],
            'laser_all_keywords_text_features': laser_all_keywords_text_features[idx].tolist(),
            'laser_item_names_text_features': laser_item_names_text_features[idx].tolist(),
            'distiluse_all_keywords_text_features': distiluse_all_keywords_text_features[idx].tolist(),
            'distiluse_item_names_text_features': distiluse_item_names_text_features[idx].tolist(),
            'e5_all_keywords_text_features': e5_all_keywords_text_features[idx].tolist(),
            'e5_item_names_text_features': e5_item_names_text_features[idx].tolist(),
        }
        for idx, item_id in enumerate(param.item_ids)
    ]


@app.post("/text/features")
async def get_text_features(param: GetTextsFeatures):
    laser_all_keywords_text_features = laser_encoder.encode_sentences(param.texts)
    # laser_item_names_text_features = laser_encoder.encode_sentences(param.item_names)
    distiluse_all_keywords_text_features = distiluse_text_encoder.encode(param.texts)
    # distiluse_item_names_text_features = distiluse_text_encoder.encode(param.item_names)
    e5_all_text_features = embed(param.texts)
    # e5_item_names_features = embed(param.item_names)

    return [
        {
            'laser_all_keywords_text_features': laser_all_keywords_text_features[idx].tolist(),
            # 'laser_item_names_text_features': laser_item_names_text_features[idx].tolist(),
            'distiluse_all_keywords_text_features': distiluse_all_keywords_text_features[idx].tolist(),
            # 'distiluse_item_names_text_features': distiluse_item_names_text_features[idx].tolist(),
            'e5_all_text_features': e5_all_text_features[idx].tolist(),
            # 'e5_item_names_features': e5_item_names_features[idx].tolist(),
        }
        for idx, _ in enumerate(param.texts)
    ]


@app.post("/image/features")
async def get_image_features(param: GetImagesFeatures):
    images = [ast.literal_eval(image) for image in param.images]
    images = [Image.open(BytesIO(image)).convert("RGB") for image in images]

    inputs = clip_processor(text=None, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_features = outputs.image_embeds.detach().cpu().numpy().tolist()

    return [{"image_features": image_features[idx]} for idx, _ in enumerate(param.images)]
