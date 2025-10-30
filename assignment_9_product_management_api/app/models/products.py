from sqlalchemy import Column, Integer, String, Float, ForeignKey
from sqlalchemy.orm import relationship
from app.database.database import Base  # adjust if needed

class Product(Base):
    __tablename__ = "products"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100), nullable=False)
    price = Column(Float, nullable=False)

    # Foreign key to Company model
    company_id = Column(Integer, ForeignKey("companies.id"))

    # Foreign key to Category model
    category_id = Column(Integer, ForeignKey("categories.id"))

    # Relationships
    company = relationship("Company", back_populates="products")
    category = relationship("Category", back_populates="products")

    