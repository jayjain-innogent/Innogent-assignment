from fastapi import APIRouter
from app.schemas.category_schema import CategoryCreate, CategoryResponse
from app.crud import category_crud as crud
from app.core.logger import logger

router = APIRouter(prefix="/categories", tags=["Categories"])

@router.post("/", response_model=CategoryResponse)
async def create_category(category: CategoryCreate):
    logger.info(f"Creating category: {category.name}")
    return await crud.create_category(category)

@router.get("/", response_model=list[CategoryResponse])
async def get_categories():
    logger.info("Fetching all categories")
    return await crud.get_all_categories()


@router.get("/{category_id}", response_model=CategoryResponse)
async def get_category(category_id: int):
    logger.info(f"Fetching category with ID: {category_id}")
    return await crud.get_category_by_id(category_id)

@router.put("/{category_id}", response_model=CategoryResponse)
async def update_category(category_id: int, category: CategoryCreate):
    logger.info(f"Updating category with ID: {category_id}")
    return await crud.update_category(category_id, category)

@router.delete("/{category_id}")
async def delete_category(category_id: int):
    logger.info(f"Deleting category with ID: {category_id}")
    return await crud.delete_category(category_id)

@router.post("/bulk", response_model=list[CategoryResponse])
async def create_multiple_categories(categories: list[CategoryCreate]):
    logger.info(f"Creating multiple categories: {[cat.name for cat in categories]}")
    return await crud.create_multiple_categories(categories)
