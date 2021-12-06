"""
run with command: uvicorn fastweb:app --reload 


(ocx) ➜  localWeb git:(master) ✗ uvicorn fastweb:app --reload 
INFO:     Uvicorn running on http://127.0.0.1:8000 (Press CTRL+C to quit)
INFO:     Started reloader process [28367] using statreload
INFO:     Started server process [28416]
INFO:     Waiting for application startup.
INFO:     Application startup complete.

"""

from fastapi import FastAPI, Request

app = FastAPI()

@app.get("/")
async def root():
    return {"message": "just rock!"}

@app.post("/")
async def receive_anything(request: Request):
    print("received the following message: \n\n", 
        await request.json(), '\n')
    return await request.json()




