from fastapi import HTTPException, status

def not_found():
    raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,message="Resource not found")

