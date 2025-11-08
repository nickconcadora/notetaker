package com.nick.notetaker.service;

import com.nick.notetaker.model.Note;
import com.nick.notetaker.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(String title, String content) {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            return noteRepository.save(note);
    }

    public void deleteNote(UUID noteId) {
        noteRepository.deleteById(noteId);
    }

    public Note viewNote(UUID noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found for this user"));
    }

    public List<Note> viewAllNotes() {
        return noteRepository.findAll();
    }
}
