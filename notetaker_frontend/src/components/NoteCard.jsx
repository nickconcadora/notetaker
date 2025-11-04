import {useEffect, useState} from "react";
import { createNote, viewAllNotes } from '../services/NoteService';
import NoteList from "./NoteList";
import './NoteCard.css';


const NoteCard = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [notes, setNotes] = useState([]);

    useEffect(() => {
        fetchNotes();
    }, []);

    const fetchNotes = async () => {
        try{
            const data = await viewAllNotes();
            setNotes(data);
        }catch (error) {
            console.error("Error fetching notes:", error);
        }

    }
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        
        if(!title && !content) {
            alert("Note cannot be empty");
            return;
        }

        try {
            const response = await createNote({title, content});
            console.log("Note created:", response);
            
            setTitle('');
            setContent('');

            await fetchNotes();
        }catch (error) {
            console.error("Error creating note", error);
        }
    }

    return (
        <div>
            <form onSubmit={handleSubmit} className="note-card">
                <h3 className="note-title">Create a New Note</h3>
                <input
                    type="text"
                    placeholder="Title"
                    className="note-title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <textarea
                    placeholder="What's on your mind?"
                    className="note-content"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />
                <button type ="submit" className = "note-button">Save Note</button>

            </form>
            <NoteList notes={notes} />
        </div>
    )
}

export default NoteCard;