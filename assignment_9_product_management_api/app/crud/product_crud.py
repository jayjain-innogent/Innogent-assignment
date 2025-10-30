from sqlalchemy.orm import Session
from fastapi import HTTPException, status
import app.schemas.product_schema as schemas
import app.models.products as models
from app.helper.httpException import not_found
from typing import List, Optional
from sqlalchemy import String, or_, cast

#Create Product
def create_product(db: Session, product: schemas.ProductCreate):
    db_product = models.Product(**product.dict())
    db.add(db_product)
    db.commit()
    db.refresh(db_product)
    return db_product


#Get All Products
def get_all_products(db: Session, offset: int = 0, limit: int = 100):
    products = db.query(models.Product).offset(offset).limit(limit).all()
    return products


#Get Product by ID
def get_product_by_id(db: Session, product_id: int):
    product = db.query(models.Product).filter(models.Product.id == product_id).first()
    if not product:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Product with id {product_id} not found"
        )
    return product


#Update Product
def update_product(db: Session, product_id: int, product: schemas.ProductCreate):
    db_product = db.query(models.Product).filter(models.Product.id == product_id).first()

    if not db_product:
        not_found()

    # Update only fields that are provided
    db_product = models.Product(**product.dict())

    db.commit()
    db.refresh(db_product)
    return db_product


#Delete Product
def delete_product(db: Session, product_id: int):
    db_product = db.query(models.Product).filter(models.Product.id == product_id).first()
    if not db_product:
        not_found()
    db.delete(db_product)
    db.commit()
    return db_product


# Search Products in CRUD
def search_products(db: Session, q: Optional[str], company_id: Optional[int] = None, skip: int = 0, limit: int = 10):
    query = db.query(models.Product)

    if q:
        search_term = f"%{q}%"
        query = query.filter(
            or_(
                models.Product.name.ilike(search_term),   # string
                cast(models.Product.price, String).ilike(search_term),  # cast numeric to string
                # For category, use category name if it's a relationship, else skip
            )
        )

    if company_id is not None:
        query = query.filter(models.Product.company_id == company_id)

    return query.offset(skip).limit(limit).all()


#Bulk Create
def create_products_bulk(db: Session, products: List[schemas.ProductCreate]):
    new_products = [models.Product(**product.dict()) for product in products]
    db.add_all(new_products)
    db.commit()
    for product in new_products:
        db.refresh(product)
    return new_products