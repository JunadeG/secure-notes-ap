package com.notes.secure_notes_api.service;


import com.notes.secure_notes_api.model.Note;
import com.notes.secure_notes_api.model.User;
import com.notes.secure_notes_api.repository.NoteRepository;
import com.notes.secure_notes_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public Note createNote(Note note){

        // 1. Get the username of the currently logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Find that user in the database
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in database"));

        // 3. Set the user on the note
        note.setUser(currentUser);

        // 4. Save the note
        return noteRepository.save(note);
    }


    public List<Note> getNotesForCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Notes not found in Database"));
        return noteRepository.findByUser(foundUser);
    }

}
