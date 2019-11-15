package com.example.demo_diplom;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import static com.example.demo_diplom.App.getKeyStore;


public class SettingsActivity extends AppCompatActivity {

    private boolean lookPassword = false;
    final String SAVED_TEXT = "saved_text";
    EditText uName;
    SharedPreferences sLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        saveLogin();
        loadText();

    }

    public void initViews() {

        uName = (EditText) findViewById(R.id.editTextPassword);

        ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);


        }

        final EditText editNewPassword = findViewById(R.id.editTextPassword);

        Button btnSavePassword = findViewById(R.id.buttonSavePassword);
        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editNewPassword.length() == 4) {
                    getKeyStore().saveNewPassword(editNewPassword.getText().toString());

                    editNewPassword.setText("");
                    Toast.makeText(SettingsActivity.this, getString(R.string.textMessageSavePassword), Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(SettingsActivity.this, getString(R.string.textErrorLengthPassword), Toast.LENGTH_SHORT).show();
                }
            }
        });

        final ImageButton btnLookPassword = findViewById(R.id.buttonLookPassword);

        btnLookPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lookPassword) {
                    btnLookPassword.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    editNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    btnLookPassword.setImageResource(R.drawable.ic_visibility_black_24dp);
                    editNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                editNewPassword.setSelection(editNewPassword.getText().length());
                lookPassword = !lookPassword;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent notesIntent = new Intent(SettingsActivity.this, PinActivity.class);
                startActivity(notesIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveLogin() {
        sLogin = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sLogin.edit();
        ed.putString(SAVED_TEXT, uName.getText().toString());
        ed.commit();
    }

    private void loadText() {
        sLogin = getPreferences(MODE_PRIVATE);
        String savedText = sLogin.getString(SAVED_TEXT, "");
        uName.setText(savedText);
        //ONLY FOR CHECKING saveLogin METHOD
      //  Toast.makeText(SettingsActivity.this, "Your logged in as " + sLogin.getString(SAVED_TEXT, ""), Toast.LENGTH_LONG).show();

    }
}