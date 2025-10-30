from fastapi import APIRouter, Query
from typing import Optional
from app.schemas.product_schema import ProductCreate, ProductResponse
from app.crud import product_crud as crud
from app.core.logger import logger

router = APIRouter(prefix="/products", tags=["Products"])


@router.post("/", response_model=ProductResponse)
async def create_product(product: ProductCreate):
    logger.info(f"Creating product: {product.name}")
    return await crud.create_product(product)


@router.get("/", response_model=list[ProductResponse])
async def get_products():
    logger.info("Fetching products")
    return await crud.get_products()


@router.get("/search", response_model=list[ProductResponse])
async def search_products(
    q: Optional[str] = Query(None),
    company_id: Optional[int] = None,
    skip: int = 0,
    limit: int = 10
):
    logger.info(f"Searching products q='{q}' company_id={company_id}")
    return await crud.search_products(q, company_id, skip, limit)


@router.put("/{product_id}", response_model=ProductResponse)
async def update_product(product_id: int, product: ProductCreate):
    logger.info(f"Updating product ID: {product_id}")
    return await crud.update_product(product_id, product)


@router.delete("/{product_id}", response_model=ProductResponse)
async def delete_product(product_id: int):
    logger.info(f"Deleting product ID: {product_id}")
    return await crud.delete_product(product_id)


@router.get("/{product_id}", response_model=ProductResponse)
async def get_product(product_id: int):
    logger.info(f"Fetching product ID: {product_id}")
    return await crud.get_product_by_id(product_id)


@router.post("/bulk", response_model=list[ProductResponse])
async def create_multiple_products(products: list[ProductCreate]):
    logger.info(f"Creating multiple products: {[p.name for p in products]}")
    return await crud.create_multiple_products(products)
