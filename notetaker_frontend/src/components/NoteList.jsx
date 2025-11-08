import React from "react";
import "./NoteList.css";

const NoteList = ({ notes, onDelete, onClick }) => {
    return (
        <div className="note-list">
            <h3>Your Notes</h3>
            {notes.length === 0 ? (
                <p>No notes yet.</p>
            ) : (
                <div className="note-items">
                    {notes.map((note) => (
                        <div key={note.id} className="note-item">
                            <h4>{note.title}</h4>
                            <p>{note.content}</p>
                            <button onClick={() => onClick(note.id)}>Summarize</button>
                            {note.summary && <p><strong>AI Summary:</strong> {note.summary}</p>}
                            <button onClick={() => onDelete(note.id)}>Delete</button>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default NoteList;
