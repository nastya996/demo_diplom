package com.example.demo_diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class ActivityPassword extends AppCompatActivity {
    private Button btn_zero;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    private Button btn_five;
    private Button btn_six;
    private Button btn_seven;
    private Button btn_eight;
    private Button btn_nine;
    private Button btn_clear;
    private TextView tvMessage;
    private LinearLayout llPassValue;
    private Keystore keyStore;
    private String resultText = "";
    private int currentInputValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


    }

    @Override
    protected void onResume() {
        super.onResume();
        keyStore = App.getInstance().getKeystore();
        // проверяем есть ли пароль
        if (keyStore.hasPin(this)) {
            initView();
            initVisualPass();
        } else {
            Intent intent = new Intent(ActivityPassword.this, SettingsActivity.class);
            startActivity(intent);
        }
    }


    private void initView() {
        btn_zero = findViewById(R.id.btn_00);
        btn_one = findViewById(R.id.btn_01);
        btn_two = findViewById(R.id.btn_02);
        btn_three = findViewById(R.id.btn_03);
        btn_four = findViewById(R.id.btn_04);
        btn_five = findViewById(R.id.btn_05);
        btn_six = findViewById(R.id.btn_06);
        btn_seven = findViewById(R.id.btn_07);
        btn_eight = findViewById(R.id.btn_08);
        btn_nine = findViewById(R.id.btn_09);
        btn_clear = findViewById(R.id.btn_clear);
        tvMessage = findViewById(R.id.tv_message);
        btn_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("0");
            }
        });
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("1");
            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("2");
            }
        });
        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("3");
            }
        });
        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("4");
            }
        });
        btn_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("5");
            }
        });
        btn_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("6");
            }
        });
        btn_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("7");
            }
        });
        btn_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("8");
            }
        });
        btn_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResult("9");
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLast();
            }
        });

    }

    // добавляем элементы по количеству символов в пароле
    private void initVisualPass() {
        llPassValue = findViewById(R.id.ll_pass_value);
        for (int i = -1; i <= Constants.PASS_LENGTH; ++i) {
            View view_pass = new View(this);
            view_pass.setLayoutParams(new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.corner_radius_lg), (int) getResources().getDimension(R.dimen.corner_radius_lg)));
            view_pass.setBackground(getResources().getDrawable(R.drawable.circle));
            view_pass.setId(i);
            llPassValue.addView(view_pass);
        }
    }

    //добавляем цифры
    private void addResult(String value) {
        if (resultText.length() < Constants.PASS_LENGTH) {
            resultText += value;
            currentInputValue++;
            changeVisualPass(currentInputValue, "visualLight");
            checkResult();
        }
    }

    //удаляем
    private void deleteLast() {
        if (resultText.length() > 0) {
            resultText = resultText.substring(0, resultText.length() - 1);
            changeVisualPass(currentInputValue, "visualDark");
            currentInputValue--;
            checkResult();
        }
    }

    //изменяем цвет кружков
    private void changeVisualPass(int currentId, String visualValue) {
        View view_pass = (View) findViewById(currentId);
        if (visualValue.equals("visualLight")) {
            view_pass.setBackground(getResources().getDrawable(R.drawable.circle_set));
        } else {
            view_pass.setBackground(getResources().getDrawable(R.drawable.circle));
        }
    }


    private void checkResult() {

        if (resultText.length() == Constants.PASS_LENGTH) {
            if (keyStore.checkPin(this, resultText)) {
                Intent intent = new Intent(ActivityPassword.this, MainActivity.class);
                startActivity(intent);
            } else {
                showErrorMessage(R.string.msg_error);
            }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        return;
    }

    // переход в настройки длоя смены пароля
    public void onClickSettings(View view) {
        Intent intent = new Intent(ActivityPassword.this, SettingsActivity.class);
        startActivity(intent);
    }
}
