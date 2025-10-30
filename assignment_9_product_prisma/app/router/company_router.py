from fastapi import APIRouter
from app.schemas.company_schema import CompanyCreate, CompanyResponse
from app.crud import company_crud as crud
from app.core.logger import logger

router = APIRouter(prefix="/companies", tags=["Companies"])

@router.post("/", response_model=CompanyResponse)
async def create_company(company: CompanyCreate):
    logger.info(f"Creating company: {company.name}")
    return await crud.create_company(company)

@router.get("/", response_model=list[CompanyResponse])
async def get_companies():
    logger.info("Fetching all companies")
    return await crud.get_all_companies()

@router.get("/{company_id}", response_model=CompanyResponse)
async def get_company(company_id: int):
    logger.info(f"Fetching company with ID: {company_id}")
    return await crud.get_company_by_id(company_id)

@router.put("/{company_id}", response_model=CompanyResponse)
async def update_company(company_id: int, company: CompanyCreate):
    logger.info(f"Updating company with ID: {company_id}")
    return await crud.update_company(company_id, company)

@router.delete("/{company_id}")
async def delete_company(company_id: int):
    logger.info(f"Deleting company with ID: {company_id}")
    return await crud.delete_company(company_id)

@router.post("/bulk", response_model=list[CompanyResponse])
async def create_multiple_companies(companies: list[CompanyCreate]):
    logger.info(f"Creating multiple companies: {[company.name for company in companies]}")
    return await crud.create_multiple_companies(companies)