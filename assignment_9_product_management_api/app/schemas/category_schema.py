from pydantic import BaseModel
from typing import List
from app.schemas.product_schema import ProductBase

class CategoryBase(BaseModel):
    name: str

class CategoryCreate(CategoryBase):
    pass

class CategoryResponse(CategoryBase):
    id: int

    class Config:
        orm_mode = True

class CategoryUpdate(CategoryBase):
    pass

class CategoryResponseWithProductsList(CategoryBase):
    id: int
    products: List[ProductBase] = []

    class Config:
        orm_mode = True