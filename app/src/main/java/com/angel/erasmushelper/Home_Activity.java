package com.angel.erasmushelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home_Activity extends AppCompatActivity {

    String TAG="MainActivity";
    Button bttnStartGenova, bttnStartUnige, bttnStartExp, bttnStartHousing, bttnStartFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Log.i(TAG, "OnCreate");

        bttnStartGenova = findViewById(R.id.buttonA);
        bttnStartUnige = findViewById(R.id.buttonB);
        bttnStartExp = findViewById(R.id.buttonC);
        bttnStartHousing = findViewById(R.id.buttonD);
        bttnStartFinder = findViewById(R.id.buttonE);

        bttnStartGenova.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("com.erasmushelper.GenovaActivity");
                startActivity(intent);
            }
        });

        bttnStartExp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("com.erasmushelper.ExperienceActivity");
                startActivity(intent);
            }
        });


    }
}
