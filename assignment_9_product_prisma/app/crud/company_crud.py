from fastapi import HTTPException
from app.core.database import db

async def create_company(data):
    existing = await db.company.find_unique(where={"name": data.name})
    if existing:
        raise HTTPException(status_code=400, detail="Company already exists")
    return await db.company.create(data={"name": data.name})

async def get_all_companies():
    return await db.company.find_many()

async def get_company_by_id(company_id: int):
    company = await db.company.find_unique(where={"id": company_id})
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    return company

async def update_company(company_id: int, data):
    existing = await db.company.find_unique(where={"id": company_id})
    if not existing:
        raise HTTPException(status_code=404, detail="Company not found")
    return await db.company.update(
        where={"id": company_id},
        data={"name": data.name}
    )

async def delete_company(company_id: int):
    existing = await db.company.find_unique(where={"id": company_id})
    if not existing:
        raise HTTPException(status_code=404, detail="Company not found")
    return await db.company.delete(where={"id": company_id})



async def create_multiple_companies(companies_data):
    created_companies = []
    for data in companies_data:
        existing = await db.company.find_unique(where={"name": data.name})
        if not existing:
            company = await db.company.create(data={"name": data.name})
            created_companies.append(company)
    return created_companies

#serch products by name or company id with pagination
async def search_companies(q: str = None, skip=0, limit=10):
    filters = {}
    if q:
        filters["name"] = {"contains": q}
    return await db.company.find_many(where=filters, skip=skip, take=limit)
    