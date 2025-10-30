package com.nick.notetaker.service;

import com.nick.notetaker.model.Note;
import com.nick.notetaker.model.User;
import com.nick.notetaker.repository.NoteRepository;
import com.nick.notetaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    public Note createNote(UUID id, String title, String content) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERROR"));

            Note createdNote = new Note();
            createdNote.setUserFK(user);
            createdNote.setTitle(title);
            createdNote.setContent(content);
            noteRepository.save(createdNote);
            return createdNote;
    }

    public Note viewNote(UUID userId, UUID noteId) {
        return noteRepository.findByIdAndUserFK_Id(noteId, userId)
                .orElseThrow(() -> new RuntimeException("Note not found for this user"));
    }

    public List<Note> viewAllNotes(UUID userId) {
        return noteRepository.findAllByUserFK_Id(userId);
    }
}
