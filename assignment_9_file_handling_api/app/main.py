from fastapi import FastAPI
from app.routers.upload import router as upload_router
from app.routers.download import router as download_router
from app.core.db import prisma    # Singleton Prisma client

app = FastAPI(title="File Handling API - Prisma + MySQL")

app.include_router(upload_router)
app.include_router(download_router)

# App startup event
@app.on_event("startup")
async def startup_event():
    if not prisma.is_connected():
        await prisma.connect()
        print("Prisma DB connected!")

# App shutdown event
@app.on_event("shutdown")
async def shutdown_event():
    if prisma.is_connected():
        await prisma.disconnect()
        print("Prisma DB disconnected!")