from dotenv import load_dotenv
import os
from fastapi import FastAPI, HTTPException
import requests
from google import genai
from fastapi.middleware.cors import CORSMiddleware



load_dotenv()
app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))

JAVA_API_BASE_URL = "http://localhost:8080/api"

@app.get("/summarize/{note_id}")
def summarize_notes(note_id: str):
    print(f"Received summarize request for note ID: {note_id}")
    note_response = requests.get(f"{JAVA_API_BASE_URL}/notes/{note_id}")
    note_response.raise_for_status()

    note_data = note_response.json()
    content = note_data.get("content")
    if not content:
        raise HTTPException(status_code=400, detail="Note has no content")

    
    summary = run_gemini(content)

    return {"noteId": note_id, "summary": summary}
    
def run_gemini(content):
    response = client.models.generate_content(
    model="gemini-2.5-flash",
    contents=f"Summarize this note in 10-15 words:\n\n{content}",
)
    print(response.text)

    return response.text
