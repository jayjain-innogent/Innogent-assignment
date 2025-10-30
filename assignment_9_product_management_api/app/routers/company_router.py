from fastapi import APIRouter, Depends, status, HTTPException
from sqlalchemy.orm import Session
from typing import List
from app.core.logger import logger
from app.database.database import get_db
from app.schemas.company_schema import CompanyCreate, CompanyResponse
from app.crud.company_crud import (
    create_company,
    get_all_companies,
    get_company_by_id,
    update_company,
    delete_company
)

router = APIRouter(
    prefix="/companies",
    tags=["Companies"]
)

# CREATE COMPANY
@router.post("/", response_model=CompanyResponse, status_code=status.HTTP_201_CREATED)
def create_company_endpoint(company: CompanyCreate, db: Session = Depends(get_db)):
    try:
        logger.info(f"Creating company with name: {company.name}")
        return create_company(db, company)
    except Exception as e:
        logger.error(f"Error creating company: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")

# GET ALL COMPANIES
@router.get("/", response_model=List[CompanyResponse])
def get_all_companies_endpoint(db: Session = Depends(get_db), skip: int = 0, limit: int = 100):
    try:
        logger.info("Fetching all companies")
        return get_all_companies(db, skip=skip, limit=limit)
    except Exception as e:
        logger.error(f"Error fetching companies: {e}", exc_info=True)
        raise HTTPException(status_code=500, detail="Internal Server Error")


# GET COMPANY BY ID
@router.get("/{company_id}", response_model=CompanyResponse)
def get_company_by_id_endpoint(company_id: int, db: Session = Depends(get_db)):
    try:
        logger.info(f"Fetching company with ID: {company_id}")
        return get_company_by_id(db, company_id)
    except HTTPException as he:
        logger.warning(f"Company not found: {he.detail}")
        raise HTTPException(status_code=he.status_code, detail=he.detail)
    
# UPDATE COMPANY
@router.put("/{company_id}", response_model=CompanyResponse)
def update_company_endpoint(company_id: int, company: CompanyCreate, db: Session = Depends(get_db)):
    try:
        logger.info(f"Updating company ID {company_id} with new data")
        return update_company(db, company_id, company)
    except HTTPException as he:
        logger.warning(f"Company update failed: {he.detail}")
        raise HTTPException(status_code=he.status_code, detail=he.detail)


# DELETE COMPANY
@router.delete("/{company_id}", status_code=status.HTTP_200_OK)
def delete_company_endpoint(company_id: int, db: Session = Depends(get_db)):
    try:
        logger.info(f"Deleting company ID {company_id}")
        return delete_company(db, company_id)
    except HTTPException as he:
        logger.warning(f"Company deletion failed: {he.detail}")
        raise HTTPException(status_code=he.status_code, detail=he.detail)
    
