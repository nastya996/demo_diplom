package com.example.demo_diplom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PassChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
    }

    public void initViews() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final EditText editNewPassword = findViewById(R.id.input_pin);

        @SuppressLint("WrongViewCast") FloatingActionButton btnSavePassword = findViewById(R.id.btn_save_pin);
        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editNewPassword.length() == 4) {
                    App.getKeystore().saveNewPassword(editNewPassword.getText().toString());

                    editNewPassword.setText("");
                    Toast.makeText(PassChange.this, getString(R.string.password_saved), Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(PassChange.this, getString(R.string.password_four_word), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

