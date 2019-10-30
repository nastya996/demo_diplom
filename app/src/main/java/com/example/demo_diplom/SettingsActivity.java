package com.example.demo_diplom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class SettingsActivity extends AppCompatActivity {

    private EditText inputPin;
    private ImageButton btnShowPin;
    boolean flag = true;
    private KeyStore pinCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }

    public void initView() {
        inputPin = findViewById(R.id.input_pin);
        btnShowPin = findViewById(R.id.btn_show);
        Button btnSavePin = findViewById(R.id.btn_save_pin);

        pinCheck = App.getKeyStore();
        btnSavePin.setOnClickListener(v -> {
            pinCheck.saveNew(inputPin.getText().toString());
        });
    }

    public void showPin(View view) {

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

