package com.angel.erasmushelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends Activity {

    TextView tvLogin, tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvLogin.setText(">Login");
                tvRegister.setText("Register");
                tvLogin.setTextColor(Color.WHITE);
                tvRegister.setTextColor(Color.BLACK);
            }
        });

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRegister.setText(">Register");
                tvLogin.setText("Login");
                tvLogin.setTextColor(Color.BLACK);
                tvRegister.setTextColor(Color.WHITE);

            }
        });


    }
}
