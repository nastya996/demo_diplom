package com.example.demo_diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;
    private Button btnClear;
    private String pin = "";
    private View circleOne;
    private View circleTwo;
    private View circleThree;
    private View circleFour;
    private SharedPreferences preferences;
    private KeyStore pinCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        inputPin();

    }

    private void initViews() {
        pinCheck = App.getKeyStore();// инициализация
        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);
        btnFour = findViewById(R.id.btn_four);
        btnFive = findViewById(R.id.btn_five);
        btnSix = findViewById(R.id.btn_six);
        btnSeven = findViewById(R.id.btn_seven);
        btnEight = findViewById(R.id.btn_eight);
        btnNine = findViewById(R.id.btn_nine);
        btnZero = findViewById(R.id.btn_zero);
        btnClear = findViewById(R.id.btn_clear);
        circleOne = findViewById(R.id.oval_1);
        circleTwo = findViewById(R.id.oval_2);
        circleThree = findViewById(R.id.oval_3);
        circleFour = findViewById(R.id.oval_4);


        preferences = getSharedPreferences("Pin", MODE_PRIVATE);
        String savedPin = preferences.getString("Pin", "");
        if (savedPin.equals("")) {                                                            //  переход в настройки
            Intent intentSettings = new Intent(this, SettingsActivity.class);
            startActivity(intentSettings);
        }
   }



    public void checkPin() {
        if (pin.length() == 4) {
            if (pinCheck.checkPin(pin)) {
                pin = "";
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
             //   clearColorCircle();
            } else {
                pin = "";
                Toast.makeText(this, "Неверный пинкод!", Toast.LENGTH_SHORT).show();
            //    clearColorCircle();


            }
        }
    }

    public void inputPin() {
        btnOne.setOnClickListener(v -> {
            pin = pin + "1";
            changeColorCircle();
            checkPin();
        });

        btnTwo.setOnClickListener(v -> {
            pin = pin + "2";
            changeColorCircle();
            checkPin();
        });

        btnThree.setOnClickListener(v -> {
            pin = pin + "3";
            changeColorCircle();
            checkPin();
        });

        btnFour.setOnClickListener(v -> {
            pin = pin + "4";
            changeColorCircle();
            checkPin();
        });

        btnFive.setOnClickListener(v -> {
            pin = pin + "5";
            changeColorCircle();
            checkPin();
        });

        btnSix.setOnClickListener(v -> {
            pin = pin + "6";
            changeColorCircle();
            checkPin();
        });

        btnSeven.setOnClickListener(v -> {
            pin = pin + "7";
            changeColorCircle();
            checkPin();
        });

        btnEight.setOnClickListener(v -> {
            pin = pin + "8";
            changeColorCircle();
            checkPin();
        });

        btnNine.setOnClickListener(v -> {
            pin = pin + "9";
            changeColorCircle();
            checkPin();
        });

        btnZero.setOnClickListener(v -> {
            pin = pin + "0";
            changeColorCircle();
            checkPin();
        });

        btnClear.setOnClickListener(v -> {
            if (pin.length() != 0) {
                if (pin.length() == 3) {
                    circleThree.setBackgroundResource(R.drawable.circle);
                } else if (pin.length() == 2) {
                    circleTwo.setBackgroundResource(R.drawable.circle);
                } else if (pin.length() == 1) {
                    circleOne.setBackgroundResource(R.drawable.circle);
                }
                pin = pin.substring(0, pin.length() - 1);
            }
        });
    }


    public void changeColorCircle() {// изменение цвета кружков

        if (pin.length() == 1) {
            circleOne.setBackgroundResource(R.drawable.circle_set);
        } else if (pin.length() == 2) {
            circleTwo.setBackgroundResource(R.drawable.circle_set);
        } else if (pin.length() == 3) {
            circleThree.setBackgroundResource(R.drawable.circle_set);
        } else if (pin.length() == 4) {
            circleFour.setBackgroundResource(R.drawable.circle_set);
        }
    }

   // public void clearColorCircle() {
  //      circleOne.setBackgroundResource(R.drawable.circle);
  //      circleTwo.setBackgroundResource(R.drawable.circle);
   //     circleThree.setBackgroundResource(R.drawable.circle);
   //     circleFour.setBackgroundResource(R.drawable.circle);
 //   }


}

