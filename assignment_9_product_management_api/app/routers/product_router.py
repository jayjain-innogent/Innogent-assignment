from fastapi import APIRouter, Depends, HTTPException, status, Query
from sqlalchemy.orm import Session
from typing import List, Optional
from app.core.logger import logger
from app.crud.product_crud import (
    create_product,
    get_product_by_id,
    get_all_products,
    update_product as crud_update_product,
    delete_product as crud_delete_product,
    search_products as crud_search_products,
    create_products_bulk as crud_create_products_bulk
)
from app.database.database import get_db
from app.schemas.product_schema import ProductCreate, ProductResponse

router = APIRouter(
    prefix="/products",
    tags=["Products"]
)

# Create a new product
@router.post("/", response_model=ProductResponse, status_code=status.HTTP_201_CREATED)
def add_product(product: ProductCreate, db: Session = Depends(get_db)):
    try:
        logger.info(f"Creating product with name: {product.name}")
        db_product = create_product(db, product)
        return db_product
    except Exception as e:
        logger.error(f"Error creating product: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")


# Search Products with optional company filter and pagination
@router.get("/search", response_model=List[ProductResponse], status_code=status.HTTP_200_OK)
def search_products(
    q: Optional[str] = Query(default=None, description="Search keyword for name/category"),
    company_id: Optional[int] = Query(default=None, description="Filter by company ID"),
    skip: int = Query(default=0, description="Number of records to skip"),
    limit: int = Query(default=10, description="Maximum number of records to return"),
    db: Session = Depends(get_db)
):
    try:
        logger.info(f"Searching products with query: {q}, company_id: {company_id}, skip: {skip}, limit: {limit}")
        return crud_search_products(db, q, company_id, skip, limit)
    except Exception as e:
        logger.error(f"Error searching products: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")


# Get all products
@router.get("/", response_model=List[ProductResponse], status_code=status.HTTP_200_OK)
def fetch_all_products(
    db: Session = Depends(get_db),
    offset: int = Query(0, description="Pagination offset"),
    limit: int = Query(100, description="Pagination limit")
):
    try:
        logger.info(f"Fetching all products with offset: {offset}, limit: {limit}")
        products = get_all_products(db, offset=offset, limit=limit)
        return products
    except Exception as e:
        logger.error(f"Error fetching products: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")


# Get a single product by ID
@router.get("/{product_id}", response_model=ProductResponse, status_code=status.HTTP_200_OK)
def fetch_product_by_id(product_id: int, db: Session = Depends(get_db)):
    try:
        logger.info(f"Fetching product with ID: {product_id}")
        product = get_product_by_id(db, product_id)
        return product
    except HTTPException as e:
        logger.warning(f"Product not found: {e.detail}")
        raise HTTPException(status_code=e.status_code, detail=e.detail)


# Update a product
@router.put("/{product_id}", response_model=ProductResponse, status_code=status.HTTP_200_OK)
def update_product(product_id: int, product: ProductCreate, db: Session = Depends(get_db)):
    try:
        logger.info(f"Updating product ID {product_id} with new data")
        updated_product = crud_update_product(db, product_id, product)
        return updated_product
    except HTTPException as e:
        logger.warning(f"Product update failed: {e.detail}")
        raise HTTPException(status_code=e.status_code, detail=e.detail)


# Delete a product
@router.delete("/{product_id}", status_code=status.HTTP_200_OK, response_model=ProductResponse)
def delete_product(product_id: int, db: Session = Depends(get_db)):
    try:
        logger.info(f"Deleting product ID {product_id}")
        deleted_product = crud_delete_product(db, product_id)
        return deleted_product
    except HTTPException as e:
        logger.warning(f"Product deletion failed: {e.detail}")
        raise HTTPException(status_code=e.status_code, detail=e.detail)


# Bulk Create
@router.post("/bulk/", response_model=List[ProductResponse], status_code=status.HTTP_201_CREATED)
def create_products_bulk(products: List[ProductCreate], db: Session = Depends(get_db)):
    try:
        logger.info(f"Creating {len(products)} products in bulk")
        created_products = crud_create_products_bulk(db, products)
        return created_products
    except Exception as e:
        logger.error(f"Error in bulk product creation: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")
