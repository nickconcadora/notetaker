package com.nick.notetaker.controller;

import com.nick.notetaker.model.Note;
import com.nick.notetaker.repository.NoteRepository;
import com.nick.notetaker.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/{id}")
    private ResponseEntity<Note> createNote(@PathVariable UUID id, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(id, note.getTitle(), note.getContent()));
    }

    @GetMapping("/{userId}/{noteId}")
    private ResponseEntity<Note> viewNote(@PathVariable UUID userId, @PathVariable UUID noteId) {
        return ResponseEntity.ok(noteService.viewNote(userId, noteId));
    }

    @GetMapping("/{userId}")
    private ResponseEntity <List<Note>> viewAllNotes(@PathVariable UUID userId) {
        return ResponseEntity.ok(noteService.viewAllNotes(userId));
    }

}
