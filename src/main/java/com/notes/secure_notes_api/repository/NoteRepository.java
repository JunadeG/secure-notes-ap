package com.notes.secure_notes_api.repository;

import com.notes.secure_notes_api.model.Note;
import com.notes.secure_notes_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);


}
