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
@RequestMapping("api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    private ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(note.getTitle(), note.getContent()));
    }

    @GetMapping("/{noteId}")
    private ResponseEntity<Note> viewNote(@PathVariable UUID noteId) {
        return ResponseEntity.ok(noteService.viewNote(noteId));
    }

    @GetMapping
    private ResponseEntity <List<Note>> viewAllNotes() {
        return ResponseEntity.ok(noteService.viewAllNotes());
    }

    @DeleteMapping("/{noteId}")
    private void deleteNote(@PathVariable UUID noteId) {
        noteService.deleteNote(noteId);
    }

}
