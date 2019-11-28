package com.example.demo_diplom;


import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class SimpleKeystore implements Keystore {
    private SharedPreferences preferences;

    public SimpleKeystore(App app) {
    }

    //проверяем есть ли пароль
    @Override
    public boolean hasPin(Context context) {
        preferences = context.getSharedPreferences(Constants.SETTINGS_PREF, Context.MODE_PRIVATE);
        String currentPin = preferences.getString(Constants.KEY_NAME, "");
        if (currentPin.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    //проверяем значение пароля
    @Override
    public boolean checkPin(Context context, String pin) {
        String md5Pin = setMd5Hex(pin);
        String currentPin = preferences.getString(Constants.KEY_NAME, "");
        if (currentPin.equals(md5Pin)) {
            return true;
        } else {
            return false;
        }
    }

    //сохраняем пароль в переменные
    @Override
    public void saveNew(Context context, String pin) {
        String md5Pin = setMd5Hex(pin);
        preferences = context.getSharedPreferences(Constants.SETTINGS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString(Constants.KEY_NAME, md5Pin);
        ed.apply();
    }

    //хэшируем значение пароля
    private String setMd5Hex(String value) {
        String md5Pin = new String(Hex.encodeHex(DigestUtils.md5(value)));
        md5Pin = new String(Hex.encodeHex(DigestUtils.md5(md5Pin + Constants.KEY_SOLT)));
        return md5Pin;
    }
}
