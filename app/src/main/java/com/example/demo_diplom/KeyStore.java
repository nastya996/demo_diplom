package com.example.demo_diplom;

import android.content.Context;

 interface Keystore {
    boolean hasPin(Context context);

    boolean checkPin(Context context, String pin);

    void saveNew(Context context, String pin);
}
