from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import relationship
from app.database.database import Base  # or app.database.database if that's correct

class Category(Base):
    __tablename__ = "categories"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100), nullable=False, unique=True)

    # Relationship with Product
    products = relationship("Product", back_populates="category")

