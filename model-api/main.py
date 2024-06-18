from io import BytesIO
from typing import List

from PIL import Image
import ast
from accelerate import Accelerator
from fastapi import FastAPI
from pydantic import BaseModel
from transformers import CLIPProcessor, CLIPModel
from laser_encoders import LaserEncoderPipeline

accelerator = Accelerator()
device = accelerator.device

model_id = "patrickjohncyh/fashion-clip"

clip_processor = CLIPProcessor.from_pretrained(model_id)
clip_model = CLIPModel.from_pretrained(model_id).to(device)  # 512

text_encoder = LaserEncoderPipeline(lang="kor_Hang")  # 1024

app = FastAPI()


class GetFeatures(BaseModel):
    item_ids: List[int]
    images: List[str]
    texts: List[str]


@app.post("/features")
async def get_features(param: GetFeatures):
    images = [ast.literal_eval(image) for image in param.images]
    images = [Image.open(BytesIO(image)).convert("RGB") for image in images]

    inputs = clip_processor(text=param.texts, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_features = outputs.image_embeds.detach().cpu().numpy().tolist()
    text_features = text_encoder.encode_sentences(param.texts)

    return [{'item_id': item_id, 'image_features': image_features[idx], 'text_features': text_features[idx].tolist()}
            for idx, item_id in enumerate(param.item_ids)]
