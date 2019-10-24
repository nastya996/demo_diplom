package com.example.demo_diplom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private EditText inputPin;
    private ImageButton btnShowPin;
    private Button btnSavePin;
    boolean flag = true;
    private KeyStore pinCheck;
    SharedPreferences preferences;
    private static final String LOGIN_FILE = "login";
    private EditText pinEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }


    private void initView() {                          //  Инициализация
        inputPin = findViewById(R.id.input_pin);
        btnShowPin = findViewById(R.id.btn_show);
        btnSavePin = findViewById(R.id.btn_save_pin);

        pinCheck = App.getKeyStore();
        btnSavePin.setOnClickListener(v -> {
            pinCheck.saveNew(inputPin.getText().toString());
        });
    }

    public void showPin(View v) {                // изм-е видимости пинкода
        if (flag) {
            btnShowPin.setImageResource(R.drawable.ic_visibility_black_24dp);
            inputPin.setInputType(InputType.TYPE_CLASS_NUMBER);
            inputPin.setSelection(inputPin.getText().length());
            flag = false;
        } else {
            btnShowPin.setImageResource(R.drawable.ic_visibility_off_black_24dp);
            inputPin.setInputType(InputType.TYPE_CLASS_NUMBER |
                    InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            inputPin.setSelection(inputPin.getText().length());
            flag = true;
        }
    }
}

 /*   //  чтение  логина
    private String getStrLog() {
        String stringStrLog;
        if (preferences.contains(LOGIN_FILE)) {
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        stringStrLog = preferences.getString(LOGIN_FILE, "");
        return stringStrLog;
    }

    //  сохранение  логина
    public void saveLogin() {
        String strLog = pinEdit.getText().toString();

  SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LOGIN_FILE, strLog);
        editor.apply();

    }

}*/





