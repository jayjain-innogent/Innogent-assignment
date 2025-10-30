# from fastapi import APIRouter, Response, HTTPException, Query
# import pandas as pd
# from app.crud.client_crud import get_clients_for_download
#
# router = APIRouter()
#
# @router.get("/download-clients")
# async def download_clients(
#     skip: int = Query(0, ge=0),
#     limit: int = Query(100, ge=1, le=1000)
# ):
#     clients = await get_clients_for_download(skip=skip, limit=limit)
#     if not clients:
#         raise HTTPException(status_code=404, detail="No clients found!")
#
#     # Pandas DataFrame bana lo for CSV conversion
#     df = pd.DataFrame([c.dict() for c in clients])
#
#     # Convert DataFrame to CSV bytes
#     csv_bytes = df.to_csv(index=False).encode('utf-8')
#
#     headers = {
#         "Content-Disposition": "attachment; filename=clients.csv"
#     }
#     return Response(content=csv_bytes, media_type="text/csv", headers=headers)


from fastapi import APIRouter, Response, HTTPException, Query
import pandas as pd
import io
from app.crud.client_crud import get_clients_for_download

router = APIRouter()

@router.get("/download-clients")
async def download_clients(
    format: str = Query("csv", regex="^(csv|xlsx)$"),
    skip: int = Query(0, ge=0),
    limit: int = Query(100, ge=1, le=1000)
):
    clients = await get_clients_for_download(skip=skip, limit=limit)
    if not clients:
        raise HTTPException(status_code=404, detail="No clients found!")

    df = pd.DataFrame([c.dict() for c in clients])

    buffer = io.BytesIO()
    if format == "csv":
        csv_str = df.to_csv(index=False)
        buffer.write(csv_str.encode("utf-8"))
        media_type = "text/csv"
        filename = "clients.csv"

    elif format == "xlsx":
        # Required: pip install openpyxl
        df.to_excel(buffer, index=False, engine="openpyxl")
        media_type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        filename = "clients.xlsx"
    else:
        raise HTTPException(status_code=400, detail="Invalid format!")

    buffer.seek(0)
    headers = {
        "Content-Disposition": f"attachment; filename={filename}"
    }
    return Response(content=buffer.read(), media_type=media_type, headers=headers)
