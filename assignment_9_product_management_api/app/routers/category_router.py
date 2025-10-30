from fastapi import APIRouter, Depends, status, HTTPException
from sqlalchemy.orm import Session
from typing import List
from app.core.logger import logger
from app.database.database import get_db
from app.schemas.category_schema import CategoryCreate, CategoryResponse
from app.crud.category_crud import (
    create_category,
    get_all_categories,
    get_category_by_id,
    update_category,
    delete_category
)

router = APIRouter(
    prefix="/categories",
    tags=["Categories"]
)

#Create
@router.post("/", response_model=CategoryResponse, status_code=status.HTTP_201_CREATED)
def create_category_endpoint(category: CategoryCreate, db: Session = Depends(get_db)):
    try:
        logger.info(f"Creating category with name: {category.name}")
        return create_category(db, category)
    except Exception as e:
        logger.error(f"Error creating category: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")

#Get All
@router.get("/", response_model=List[CategoryResponse])
def get_all_categories_endpoint(db: Session = Depends(get_db), skip: int = 0, limit: int = 100):
    try:
        logger.info("Fetching all categories")
        return get_all_categories(db, skip, limit)
    except Exception as e:
        logger.error(f"Error fetching categories: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")

#Get By ID
@router.get("/{category_id}", response_model=CategoryResponse)
def get_category_by_id_endpoint(category_id: int, db: Session = Depends(get_db)):
    try:
        return get_category_by_id(db, category_id)
    except HTTPException as he:
        logger.warning(f"Category not found: {he.detail}")
        raise HTTPException(status_code=he.status_code, detail=he.detail)

#Update
@router.put("/{category_id}", response_model=CategoryResponse)
def update_category_endpoint(category_id: int, category: CategoryCreate, db: Session = Depends(get_db)):
    try:
        logger.info(f"Updating category ID {category_id} with new data")
        return update_category(db, category_id, category)
    except HTTPException as he:
        logger.warning(f"Category update failed: {he.detail}")
        raise HTTPException(status_code=he.status_code, detail=he.detail)

#Delete
@router.delete("/{category_id}", status_code=status.HTTP_200_OK)
def delete_category_endpoint(category_id: int, db: Session = Depends(get_db)):
    try:
        logger.info(f"Deleting category ID {category_id}")
        return delete_category(db, category_id)
    except HTTPException as he:
        logger.warning(f"Category deletion failed: {he.detail}")
        raise HTTPException(status_code=he.status_code, detail=he.detail)

