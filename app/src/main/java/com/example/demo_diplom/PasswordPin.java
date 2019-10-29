package com.example.demo_diplom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordPin extends AppCompatActivity {
    private String enteredUserPassword = "";
    private int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        NumberButtonClickListener numberListener = new NumberButtonClickListener();

        int[] buttonIdNumber = new int[]{R.id.btn_one, R.id.btn_two
                , R.id.btn_three, R.id.btn_four
                , R.id.btn_five, R.id.btn_six
                , R.id.btn_seven, R.id.btn_eight
                , R.id.btn_nine, R.id.btn_zero
                , R.id.btn_clear};

        for (int buttonId : buttonIdNumber) {
            findViewById(buttonId).setOnClickListener(numberListener);
        }

        images = new int[]{R.id.oval_1, R.id.oval_2
                , R.id.oval_3, R.id.oval_4};
    }

    class NumberButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int idButtonClicked;

            if (enteredUserPassword.length() < 4) {
                idButtonClicked = v.getId();
            } else if (v.getId() == R.id.btn_clear) {
                idButtonClicked = v.getId();
            } else {
                idButtonClicked = 0;
            }

            switch (idButtonClicked) {
                case R.id.btn_zero:
                    enteredUserPassword += "0";
                    break;
                case R.id.btn_one:
                    enteredUserPassword += "1";
                    break;
                case R.id.btn_two:
                    enteredUserPassword += "2";
                    break;
                case R.id.btn_three:
                    enteredUserPassword += "3";
                    break;
                case R.id.btn_four:
                    enteredUserPassword += "4";
                    break;
                case R.id.btn_five:
                    enteredUserPassword += "5";
                    break;
                case R.id.btn_six:
                    enteredUserPassword += "6";
                    break;
                case R.id.btn_seven:
                    enteredUserPassword += "7";
                    break;
                case R.id.btn_eight:
                    enteredUserPassword += "8";
                    break;
                case R.id.btn_nine:
                    enteredUserPassword += "9";
                    break;
                case R.id.btn_clear:
                    if (enteredUserPassword.length() != 0) {
                        enteredUserPassword = enteredUserPassword.substring(0, enteredUserPassword.length() - 1);
                    }
                    break;
            }

            for (int i = 0; i < images.length; i++) {
                if (i < enteredUserPassword.length()) {
                    findViewById(images[i]).setBackgroundColor(Color.rgb(255, 193, 7));
                } else {
                    findViewById(images[i]).setBackgroundColor(Color.rgb(80, 80, 80));
                }
            }

            if (enteredUserPassword.length() == 4) {
                if (App.getKeystore().checkPassword(enteredUserPassword)) {
                    finish();
                } else {
                    Toast.makeText(PasswordPin.this, getString(R.string.password_not_right), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}


