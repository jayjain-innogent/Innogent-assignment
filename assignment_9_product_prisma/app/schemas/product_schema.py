from pydantic import BaseModel

class ProductBase(BaseModel):
    name: str
    price: float
    companyId: int
    categoryId: int

class ProductCreate(ProductBase):
    pass

class ProductResponse(ProductBase):
    id: int

    model_config = {
        "from_attributes": True  # <-- Fix for Prisma Python
    }
