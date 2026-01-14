package com.notes.secure_notes_api.controller;

import com.notes.secure_notes_api.model.Note;
import com.notes.secure_notes_api.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getNotes(){
        return noteService.getNotesForCurrentUser();
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
       Note newNote = noteService.createNote(note);
       return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }


}
