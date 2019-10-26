package com.example.demo_diplom;

import android.app.Application;

public class App extends Application {


    private static KeyStore keyStore;

    @Override
    public void onCreate() {
        super.onCreate();

        keyStore = new CheckPin(this);
    }

    public static KeyStore getKeyStore() {
        return keyStore;
    }


}
