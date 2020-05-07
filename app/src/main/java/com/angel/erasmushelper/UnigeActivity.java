package com.angel.erasmushelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UnigeActivity extends AppCompatActivity {
    Button findCourse, applyForm;
    String urlCourse = "https://courses.unige.it/";
    String urlApplyForm ="https://www.studenti.unige.it/areaint/foreignstudents/erasmus/english";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unige_activity);
        findCourse = findViewById(R.id.FindCourseBttn);
        applyForm = findViewById(R.id.ApplyBttn);

        findCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriCourse = Uri.parse(urlCourse);
                Intent intent = new Intent(Intent.ACTION_VIEW, uriCourse);
                startActivity(intent);
            }
        });
        applyForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriApplyForm = Uri.parse(urlApplyForm);
                Intent intent = new Intent(Intent.ACTION_VIEW, uriApplyForm);
                startActivity(intent);
            }
        });
    }

}
