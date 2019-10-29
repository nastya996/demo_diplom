package com.example.demo_diplom;

import java.util.Date;
import java.util.List;

public interface NoteRepository { List<Note> getNotes();

    Note getNote(String idNote);
    void saveNote(Note note);
    void updateNote(String idNote, String title, String subtitle, Date deadline);
    void updateNote(String idNote, String title, String subtitile);
    void deleteNote(String idNote);
}
