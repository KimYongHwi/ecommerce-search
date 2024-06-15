from io import BytesIO
from typing import List

from PIL import Image
from accelerate import Accelerator
from fastapi import FastAPI
from pydantic import BaseModel
from transformers import CLIPProcessor, CLIPModel

accelerator = Accelerator()
device = accelerator.device

model_id = "patrickjohncyh/fashion-clip"

clip_processor = CLIPProcessor.from_pretrained(model_id)
clip_model = CLIPModel.from_pretrained(model_id).to(device)

app = FastAPI()


class GetFeatures(BaseModel):
    item_ids: List[int]
    images: List[str]
    texts: List[str]


@app.get("/features")
async def get_features(param: GetFeatures):
    images = [image for image in param.images]
    images = [Image.open(BytesIO(image)).convert("RGB") for image in images]

    inputs = clip_processor(text=param.texts, images=images, return_tensors="pt", padding=True).to(device)
    outputs = clip_model(**inputs)

    image_embeds = outputs.image_embeds.detach().cpu().numpy().tolist()
    text_embeds = outputs.text_embeds.detach().cpu().numpy().tolist()

    return [{item_no: {'image_features': image_embeds[idx], 'text_features': text_embeds[idx]}} for idx, item_no in
            enumerate(param.item_nos)]
