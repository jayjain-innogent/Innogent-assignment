from pydantic import BaseModel
from typing import Optional

class ProductBase(BaseModel):
    name: str
    price : float

class CompanyBase(BaseModel):
    name: str
    address: Optional[str] = None

class CompanyCreate(CompanyBase):
    pass

class CompanyResponse(CompanyBase):
    id: int
    products: list[ProductBase] = []

    class Config:
        orm_mode = True