package com.angel.erasmushelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bttStartHomeActivity, bttLoginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttStartHomeActivity = findViewById(R.id.BttnHome);
        bttStartHomeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.erasmushelper.Home_Activity");
                startActivity(intent);
            }
        });

        bttLoginRegister = findViewById(R.id.BttnRegLog);
        bttLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.erasmushelper.RegisterActivity");
                startActivity(intent);
            }
        });
    }
}
