from dotenv import load_dotenv
import os
from fastapi import FastAPI, HTTPException


# Load .env variables
load_dotenv()

# Retrieve them
api_key = os.getenv("OPENAI_API_KEY")

if not api_key:
    raise RuntimeError("API_KEY NOT SET. Put it in .env or your environment.")



