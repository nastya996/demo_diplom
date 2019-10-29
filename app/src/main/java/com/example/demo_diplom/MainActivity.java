package com.example.demo_diplom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity  extends AppCompatActivity {

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
    private View ovalOne;
    private View ovalTwo;
    private View ovalThree;
    private View ovalFour;
    private SharedPreferences pref;
    private KeyStore pinCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        inputPin();
    }

    public void initView() {  //  инициализация
        pinCheck = App.getKeyStore();

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
        ovalOne = findViewById(R.id.oval_1);
        ovalTwo = findViewById(R.id.oval_2);
        ovalThree = findViewById(R.id.oval_3);
        ovalFour = findViewById(R.id.oval_4);

        pref = getSharedPreferences("Pin", MODE_PRIVATE);
        String savedPin = pref.getString("Pin", "");
        if (savedPin.equals("")) {
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
                clearColorOval();
            } else {
                pin = "";
                Toast.makeText(this, "Введен неверный пинкод!", Toast.LENGTH_LONG).show();
                clearColorOval();
            }
        }
    }

    public void inputPin() {
        btnOne.setOnClickListener(v -> {
            pin = pin + "1";
            changeColorOval();
            checkPin();
        });

        btnTwo.setOnClickListener(v -> {
            pin = pin + "2";
            changeColorOval();
            checkPin();
        });

        btnThree.setOnClickListener(v -> {
            pin = pin + "3";
            changeColorOval();
            checkPin();
        });

        btnFour.setOnClickListener(v -> {
            pin = pin + "4";
            changeColorOval();
            checkPin();
        });

        btnFive.setOnClickListener(v -> {
            pin = pin + "5";
            changeColorOval();
            checkPin();
        });

        btnSix.setOnClickListener(v -> {
            pin = pin + "6";
            changeColorOval();
            checkPin();
        });

        btnSeven.setOnClickListener(v -> {
            pin = pin + "7";
            changeColorOval();
            checkPin();
        });

        btnEight.setOnClickListener(v -> {
            pin = pin + "8";
            changeColorOval();
            checkPin();
        });

        btnNine.setOnClickListener(v -> {
            pin = pin + "9";
            changeColorOval();
            checkPin();
        });

        btnZero.setOnClickListener(v -> {
            pin = pin + "0";
            changeColorOval();
            checkPin();
        });

        btnClear.setOnClickListener(v -> {
            if (pin.length() != 0) {
                if (pin.length() == 3) {
                    ovalThree.setBackgroundResource(R.drawable.circle);
                } else if (pin.length() == 2) {
                    ovalTwo.setBackgroundResource(R.drawable.circle);
                } else if (pin.length() == 1) {
                    ovalOne.setBackgroundResource(R.drawable.circle);
                }
                pin = pin.substring(0, pin.length() - 1);
            }
        });
    }

    public void changeColorOval() {

        if (pin.length() == 1) {
            ovalOne.setBackgroundResource(R.drawable.circle_set);
        } else if (pin.length() == 2) {
            ovalTwo.setBackgroundResource(R.drawable.circle_set);
        } else if (pin.length() == 3) {
            ovalThree.setBackgroundResource(R.drawable.circle_set);
        } else if (pin.length() == 4) {
            ovalFour.setBackgroundResource(R.drawable.circle_set);
        }
    }

    public void clearColorOval() {
        ovalOne.setBackgroundResource(R.drawable.circle);
        ovalTwo.setBackgroundResource(R.drawable.circle);
        ovalThree.setBackgroundResource(R.drawable.circle);
        ovalFour.setBackgroundResource(R.drawable.circle);
    }


}
