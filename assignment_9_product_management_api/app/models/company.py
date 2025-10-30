from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import relationship
from app.database.database import Base  # adjust if using app.database.database

class Company(Base):
    __tablename__ = "companies"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100), unique=True, index=True, nullable=False)
    location = Column(String(255), nullable=False)

    # Relationship with Product
    products = relationship("Product", back_populates="company")
