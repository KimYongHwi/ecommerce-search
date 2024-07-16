from typing import List

from pydantic import BaseModel


class GetAllFeatures(BaseModel):
    item_ids: List[int]
    image_urls: List[str]
    texts: List[str]


class GetTextsFeatures(BaseModel):
    texts: List[str]


class GetImagesFeatures(BaseModel):
    images: List[str]
