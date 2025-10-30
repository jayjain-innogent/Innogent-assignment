from fastapi import FastAPI
from app.core.database import db
from app.core.logger import logger
from app.router import company_router as company
from app.router import category_router as category
from app.router import product_router as product

app = FastAPI(title="Product Management API - Prisma + MySQL")

app.include_router(company.router)
app.include_router(category.router)
app.include_router(product.router)

@app.on_event("startup")
async def startup():
    logger.info("Connecting to database...")
    await db.connect()
    logger.info("Database connected successfully")

@app.on_event("shutdown")
async def shutdown():
    logger.info("Disconnecting from database...")
    await db.disconnect()
    logger.info("Database disconnected successfully")

