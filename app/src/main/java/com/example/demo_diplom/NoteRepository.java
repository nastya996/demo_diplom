package com.example.demo_diplom;

import android.content.ClipData;
import android.provider.ContactsContract;

import java.util.Date;
import java.util.List;

public interface NoteRepository {
    List<ContactsContract.CommonDataKinds.Note> getNotes();

    ContactsContract.CommonDataKinds.Note getNote(String idNote);
    void saveNote(ContactsContract.CommonDataKinds.Note note);
    void updateNote(String idNote, String title, String subtitle, Date deadline);
    void updateNote(String idNote, String title, String subtitile);
    void deleteNote(String idNote);
}
