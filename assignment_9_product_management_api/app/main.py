from fastapi import FastAPI, Request
from fastapi.middleware.cors import CORSMiddleware
from app.database.database import Base, engine, get_db
from app.routers import product_router, company_router, category_router
from app.database.database import SessionLocal
from app.models.company import Company
from app.models.category import Category
from app.models.products import Product
from app.core.logger import logger

#Create all database tables (only for development — in production use Alembic)
Base.metadata.create_all(bind=engine)



#Initialize the FastAPI app
app = FastAPI(
    title="Product Management API",
    description="A simple CRUD API for managing products",
    version="1.0.0",
)

@app.middleware("http")
async def log_requests(request: Request, call_next):
    logger.info(f"Incoming request: {request.method} {request.url}")
    response = await call_next(request)
    logger.info(f"Response status: {response.status_code}")
    return response

#Include Routers
app.include_router(product_router.router)
app.include_router(company_router.router)
app.include_router(category_router.router)

#Root endpoint
@app.get("/")
def root():
    logger.info("Root endpoint accessed")
    return {"message": "Welcome to the Product Management API"}
