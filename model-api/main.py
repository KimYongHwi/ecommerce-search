from io import BytesIO
from typing import List

import requests
from PIL import Image
from accelerate import Accelerator
from fastapi import FastAPI
from transformers import CLIPProcessor, CLIPModel

accelerator = Accelerator()
device = accelerator.device

model_id = "patrickjohncyh/fashion-clip"

clip_processor = CLIPProcessor.from_pretrained(model_id)
clip_model = CLIPModel.from_pretrained(model_id).to(device)

app = FastAPI()


@app.get("/features")
async def get_features(item_nos: List[int], image_urls: List[str], texts: List[str]):
    image_res = [requests.get(url) for url in image_urls]
    images = [Image.open(BytesIO(res.content)).convert("RGB") for res in image_res]

    inputs = clip_processor(text=texts, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_embeds = outputs.image_embeds.detach().cpu().numpy().tolist()
    text_embeds = outputs.text_embeds.detach().cpu().numpy().tolist()

    return [{item_no: {'image_features': image_embeds[idx], 'text_features': text_embeds[idx]}} for idx, item_no in enumerate(item_nos)]
