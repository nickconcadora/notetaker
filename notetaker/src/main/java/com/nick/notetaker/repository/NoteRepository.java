package com.nick.notetaker.repository;

import com.nick.notetaker.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    Optional<Note> findByIdAndUserFK_Id(UUID noteId, UUID userId);
    List<Note> findAllByUserFK_Id(UUID userId);


}
