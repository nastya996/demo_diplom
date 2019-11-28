package com.example.demo_diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Keystore keyStore;
    private EditText editTextPassword;
    private ImageButton buttonLookPassword;
    private Button buttonSavePassword;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        keyStore = App.getInstance().getKeystore();
        initView();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.settings));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Constants.PASS_LENGTH)}); //задаем максимальную длину вводимого значения

        //показ и скрытие значений пароля
        buttonLookPassword = findViewById(R.id.buttonLookPassword);
        buttonLookPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showHidePass(motionEvent);
                return true;
            }
        });

        //сохранение пароля
        buttonSavePassword = findViewById(R.id.buttonSavePassword);
        buttonSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePassword();
            }
        });
    }

    private void showHidePass(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                editTextPassword.setInputType(InputType.TYPE_CLASS_NUMBER);
                buttonLookPassword.setImageResource(R.drawable.ic_visibility_black_24dp);
                break;
            default:
                editTextPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                buttonLookPassword.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                break;
        }
    }

    private void savePassword() {
        String passValue = editTextPassword.getText().toString();
        // длина пароля
        if (passValue.length() < Constants.PASS_LENGTH || passValue == null) {
            showErrorMessage(R.string.msg_error);
            editTextPassword.setText("");
        } else {
            keyStore.saveNew(this, passValue);
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void showErrorMessage(final int message) {
        tvMessage = findViewById(R.id.tv_message);
        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(getResources().getText(message));
            }

            public void onFinish() {
                tvMessage.setVisibility(View.GONE);
            }
        };
        timer.start();
    }
}
