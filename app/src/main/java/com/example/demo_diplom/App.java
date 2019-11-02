package com.example.demo_diplom;

import android.app.Application;

public class App extends Application {

    public static NoteRepository noteRepository;
    public static KeyStore hashedKeyStore;
    public static final String FILE_NAME = "data.json";

    @Override
    public void onCreate() {
        super.onCreate();


        noteRepository = new MyNoteRepository(this, FILE_NAME);
        hashedKeyStore = (KeyStore) new HashedKeyStore();
    }

    // Возвращаем интерфейс, а не конкретную реализацию!
    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }

    // Возвращаем интерфейс, а не конкретную реализацию!
    public static KeyStore getKeystore() {
        return hashedKeyStore;
    }
}



