import os
from dotenv import load_dotenv

load_dotenv()

class Settings:
    DB_USER : str = os.getenv("DB_USER")
    DB_PASSWORD : str = os.getenv("DB_PASSWORD")
    DB_HOST : str = os.getenv("DB_HOST")
    DB_NAME : str = os.getenv("DB_NAME")
    DB_PORT : str = os.getenv("DB_PORT")

    DATABASE_URL = (
        f"mysql+mysqlconnector://{DB_USER}:{DB_PASSWORD}"
        f"@{DB_HOST}:{DB_PORT}/{DB_NAME}"
    )

settings = Settings()


