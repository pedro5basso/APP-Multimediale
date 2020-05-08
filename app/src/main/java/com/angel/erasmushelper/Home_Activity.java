package com.angel.erasmushelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;

public class Home_Activity extends AppCompatActivity {

    String TAG="MainActivity";
    ImageButton bttnStartGenova, bttnStartUnige, bttnStartExp, bttnStartHousing, bttnStartFinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);



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

        bttnStartUnige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.erasmushelper.UnigeActivity");
                startActivity(intent);
            }
        });


    }
}
