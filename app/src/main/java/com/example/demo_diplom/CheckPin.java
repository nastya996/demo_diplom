package com.example.demo_diplom;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CheckPin implements KeyStore {

    private SharedPreferences pref;
    private Context contx;

    public CheckPin(Context context) {
        pref = context.getSharedPreferences("Pin", MODE_PRIVATE);
        this.contx = context;
    }


    @Override
    public boolean checkPin(String pin) {
        return pin.equals(pref.getString("Pin", ""));
    }

    @Override
    public void saveNew(String pin) {
        SharedPreferences.Editor myEditor = pref.edit();
        myEditor.putString("Pin", pin);
        myEditor.apply();
    }
}