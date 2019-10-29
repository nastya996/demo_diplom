package com.example.demo_diplom;

import android.app.Application;

public class App extends Application {

    public static NoteRepository noteRepository;
    public static KeyStore hashedKeystore;
    public static final String FILE_NAME = "data.json";


    @Override
    public void onCreate() {
        super.onCreate();


        noteRepository = new IsNoteRepository(this, FILE_NAME);
        hashedKeystore = new HashKeyStore();

    }


    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }


    public static KeyStore getKeystore() {
        return hashedKeystore;
    }


}
