from sqlalchemy.orm import Session
from fastapi import HTTPException, status
from app.models.company import Company
from app.schemas.company_schema import CompanyCreate

# CREATE COMPANY
def create_company(db: Session, company: CompanyCreate):
    existing_company = db.query(Company).filter(Company.name == company.name).first()
    if existing_company:
        raise HTTPException(
            status_code=status.HTTP_400_BAD_REQUEST,
            detail=f"Company '{company.name}' already exists."
        )

    new_company = Company(name=company.name, location=company.address or "Unknown")
    db.add(new_company)
    db.commit()
    db.refresh(new_company)
    return new_company


# GET ALL COMPANIES
def get_all_companies(db: Session, skip: int = 0, limit: int = 100):
    return db.query(Company).offset(skip).limit(limit).all()


# GET COMPANY BY ID
def get_company_by_id(db: Session, company_id: int):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Company with ID {company_id} not found."
        )
    return company


# UPDATE COMPANY
def update_company(db: Session, company_id: int, updated_data: CompanyCreate):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Company with ID {company_id} not found."
        )

    company.name = updated_data.name
    company.location = updated_data.address or company.location

    db.commit()
    db.refresh(company)
    return company


# DELETE COMPANY
def delete_company(db: Session, company_id: int):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Company with ID {company_id} not found."
        )

    db.delete(company)
    db.commit()
    return {"message": f"Company '{company.name}' deleted successfully."}
