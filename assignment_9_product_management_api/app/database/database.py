from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base
from app.core.config import settings
from app.core.logger import logger 


#Creating engine
engine = create_engine(settings.DATABASE_URL,
                       echo = True, 
                       pool_pre_ping = True
                       )

#Creating a configured "Session" class
SessionLocal = sessionmaker(autocommit=False,
                            autoflush=False,
                            bind=engine)

#Creating a Base class for our models to inherit from
Base = declarative_base()

# Dependency to get DB session
def get_db():
    db = SessionLocal()
    try:
        logger.info("Database session started")
        yield db
    except Exception as e:
        logger.error(f"Database session error: {e}", exc_info=True)  # logs traceback too
    finally:
        db.close()
        logger.info("Database session closed")


