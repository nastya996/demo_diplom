package com.example.demo_diplom;

import android.app.Application;

public class App extends Application {
    private static KeyStore keyStore;



    public static KeyStore getKeyStore() {
        return keyStore;
    }


}
