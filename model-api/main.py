import ast
from concurrent.futures import ThreadPoolExecutor
from io import BytesIO

import requests
from PIL import Image
from accelerate import Accelerator
from fastapi import FastAPI
from laser_encoders import LaserEncoderPipeline
from requests.adapters import HTTPAdapter, Retry
from transformers import CLIPProcessor, CLIPModel

from request import GetAllFeatures, GetTextsFeatures, GetImagesFeatures

accelerator = Accelerator()
device = accelerator.device

model_id = "patrickjohncyh/fashion-clip"

clip_processor = CLIPProcessor.from_pretrained(model_id)
clip_model = CLIPModel.from_pretrained(model_id).to(device)  # 512

text_encoder = LaserEncoderPipeline(lang="kor_Hang")  # 1024

app = FastAPI()


headers = {'User-Agent': "Mozilla/5.0 (X11; Linux i686; rv:64.0) Gecko/20100101 Firefox/64.0"},
s = requests.Session()
s.headers = headers

retries = Retry(
    total=5,
    backoff_factor=0.1,
    status_forcelist=[400, 500, 502, 503, 504],
)

s.mount('http://', HTTPAdapter(max_retries=retries, pool_connections=20))


def get_image(url):
    return s.get(url, stream=True).raw


def get_image_raws(urls):
    with ThreadPoolExecutor(max_workers=8) as executor:
        return list(executor.map(get_image, urls))


@app.post("/features")
async def get_features(param: GetAllFeatures):
    images = get_image_raws(param.image_urls)
    images = [Image.open(image).convert("RGB") for image in images]
    inputs = clip_processor(text=param.texts, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_features = outputs.image_embeds.detach().cpu().numpy().tolist()
    text_features = text_encoder.encode_sentences(param.texts)

    return [{'item_id': item_id, 'image_features': image_features[idx], 'text_features': text_features[idx].tolist()}
            for idx, item_id in enumerate(param.item_ids)]


@app.post("/text/features")
async def get_text_features(param: GetTextsFeatures):
    text_features = text_encoder.encode_sentences(param.texts)

    return [{"text_features": text_features[idx]} for idx, _ in enumerate(param.texts)]


@app.post("/image/features")
async def get_image_features(param: GetImagesFeatures):
    images = [ast.literal_eval(image) for image in param.images]
    images = [Image.open(BytesIO(image)).convert("RGB") for image in images]

    inputs = clip_processor(text=None, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_features = outputs.image_embeds.detach().cpu().numpy().tolist()

    return [{"image_features": image_features[idx]} for idx, _ in enumerate(param.images)]
