import { useEffect, useState } from "react";
import { createNote, viewAllNotes, deleteNote } from '../services/NoteService';
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

        const data = await viewAllNotes();
        console.log("Fetched notes:", data);
        setNotes(data);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!title && !content) {
            alert("Note cannot be empty");
            return;
        }

        await createNote({ title, content });

        setTitle('');
        setContent('');

        await fetchNotes();
    }

    const handleDelete = async (id) => {
        const status = await deleteNote(id);

        if (status === 200 || status === 204) {
            // remove from local state
            const updatedNotes = notes.filter((note) => note.id !== id);
            setNotes(updatedNotes);
        }
    };
    const handleSummarize = async (noteId) => {
        const response = await fetch(`http://localhost:8000/summarize/${noteId}`);
        if (!response.ok) throw new Error("Error summarizing note");
        const data = await response.json();

        console.log("AI summary received:", data.summary);

        setNotes(prev =>
            prev.map(n => (n.id === noteId ? { ...n, summary: data.summary } : n))
        );
    };

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
                <button type="submit" className="note-button">Save Note</button>
            </form>

            <NoteList notes={notes} onDelete={handleDelete} onClick={handleSummarize}/>
        </div>
    )
}

export default NoteCard;