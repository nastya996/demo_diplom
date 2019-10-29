package com.example.demo_diplom;

import android.content.Context;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class BaseNotes implements NoteRepository {

    RealmConfiguration realmConfig;

    public BaseNotes(Context context) {
        Realm.init(context);
        realmConfig = new RealmConfiguration.Builder().name("Notes.realm").build();
    }

    @Override
    public List<Note> getNotes() {
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();

        RealmResults<Note> results = realm.where(Note.class).findAll().sort("deadline", Sort.ASCENDING);
        List<Note> notes = realm.copyFromRealm(results);

        realm.commitTransaction();
        realm.close();
        return notes;
    }

    @Override
    public Note getNote(String idNote) {
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();

        Note realmNote = realm.where(Note.class).equalTo("idNote", idNote).findFirst();
        Note note = realm.copyFromRealm(realmNote);

        realm.commitTransaction();
        realm.close();
        return note;
    }

    @Override
    public void saveNote(Note note) {
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();

        realm.insertOrUpdate(note);

        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateNote(String idNote, String title, String subtitle, Date deadline) {
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();

        Note realmNote = realm.where(Note.class).equalTo("idNote", idNote).findFirst();

        realmNote.setTitle(title);
        realmNote.setSubtitle(subtitle);
        realmNote.setDeadline(deadline);


        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateNote(String idNote, String title, String subtitile) {
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();

        Note realmNote = realm.where(Note.class).equalTo("idNote", idNote).findFirst();

        realmNote.setTitle(title);
        realmNote.setSubtitle(subtitile);


        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteNote(String idNote) {
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();

        Note realmNote = realm.where(Note.class).equalTo("idNote", idNote).findFirst();
        realmNote.deleteFromRealm();

        realm.commitTransaction();
        realm.close();
    }
}
