from pydantic import BaseModel
from typing import Optional



class ProductBase(BaseModel):
    name: str
    price : float
    company_id: int
    category_id: int

class ProductCreate(ProductBase):
    pass

class ProductResponse(ProductBase):
    id: int 

    class Config:
        orm_mode = True


