package com.example.demo_diplom;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class TestPin implements KeyStore {

    private SharedPreferences preferences;
    private Context context;

    public TestPin(Context context) {
        preferences = context.getSharedPreferences("Pin", MODE_PRIVATE);
        this.context = context;
    }

    @Override
    public boolean checkPin(String pin) {
        return pin.equals(preferences.getString("Pin", ""));
    }

    @Override
    public void saveNew(String pin) {
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("Pin", pin);
        myEditor.apply();
    }
}


