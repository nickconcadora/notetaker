import axios from 'axios';


const API_URL = "/api/notes";

export const createNote = async (noteData) => {
    const response = await axios.post(API_URL, noteData);
    return response.data;
}

export const viewAllNotes = async () => {
    const response = await axios.get(API_URL);
    return response.data;
}

export const deleteNote = async (id) => {
    const response = await axios.delete(`${API_URL}/${id}`);
    return response.status;
}