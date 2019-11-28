package com.example.demo_diplom;

import java.util.List;

public interface NotesRepository {
    Note getNoteById(int id);

    List<Note> getNotes();

    void addNote(Note note);

    void deleteNote(Note note);

    void updateNote(Note note);
}