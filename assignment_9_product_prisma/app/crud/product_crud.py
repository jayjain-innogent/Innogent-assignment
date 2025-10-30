from fastapi import HTTPException
from app.core.database import db
from app.schemas.product_schema import ProductResponse

async def create_product(data):
    # Validate foreign keys
    company = await db.company.find_unique(where={"id": data.company_id})
    category = await db.category.find_unique(where={"id": data.category_id})

    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    if not category:
        raise HTTPException(status_code=404, detail="Category not found")

    existing = await db.product.find_first(
        where={"name": data.name, "companyId": data.company_id}
    )
    if existing:
        raise HTTPException(status_code=400, detail="Product already exists for this company")

    return await db.product.create(
        data={
            "name": data.name,
            "price": data.price,
            "companyId": data.company_id,
            "categoryId": data.category_id,
        }
    )

async def get_products():
    products = await db.product.find_many()
    return products  # unpack dict into Pydantic


async def get_product_by_id(product_id: int):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return product 

async def search_products(q: str = None, company_id: int = None, skip=0, limit=10):
    filters = {}
    if q:
        filters["OR"] = [{"name": {"contains": q}}]
    if company_id:
        filters["companyId"] = company_id
    return await db.product.find_many(where=filters, skip=skip, take=limit)

async def update_product(product_id: int, data):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return await db.product.update(
        where={"id": product_id},
        data=data.dict(exclude_unset=True)
    )

async def delete_product(product_id: int):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return await db.product.delete(where={"id": product_id})

async def create_multiple_products(products_data):
    created_products = []
    for data in products_data:
        # Validate foreign keys
        company = await db.company.find_unique(where={"id": data.company_id})
        category = await db.category.find_unique(where={"id": data.category_id})

        if not company or not category:
            continue  # Skip invalid entries

        existing = await db.product.find_first(
            where={"name": data.name, "companyId": data.company_id}
        )
        if not existing:
            product = await db.product.create(
                data={
                    "name": data.name,
                    "price": data.price,
                    "companyId": data.company_id,
                    "categoryId": data.category_id,
                }
            )
            created_products.append(product)
    return created_products