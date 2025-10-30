from fastapi import HTTPException
from app.core.database import db

async def create_category(data):
    existing = await db.category.find_unique(where={"name": data.name})
    if existing:
        raise HTTPException(status_code=400, detail="Category already exists")
    return await db.category.create(data={"name": data.name})

async def get_all_categories():
    return await db.category.find_many()

async def get_category_by_id(category_id: int):
    category = await db.category.find_unique(where={"id": category_id})
    if not category:
        raise HTTPException(status_code=404, detail="Category not found")
    return category

async def update_category(category_id: int, data):
    existing = await db.category.find_unique(where={"id": category_id})
    if not existing:
        raise HTTPException(status_code=404, detail="Category not found")
    return await db.category.update(
        where={"id": category_id},
        data={"name": data.name}
    )

async def delete_category(category_id: int):
    existing = await db.category.find_unique(where={"id": category_id})
    if not existing:
        raise HTTPException(status_code=404, detail="Category not found")
    return await db.category.delete(where={"id": category_id})


async def create_multiple_categories(data_list):
    created_categories = []
    for data in data_list:
        existing = await db.category.find_unique(where={"name": data.name})
        if not existing:
            category = await db.category.create(data={"name": data.name})
            created_categories.append(category)
    return created_categories