package com.example.airbag.airbag.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.airbag.airbag.R;

public class ReportSuspicion extends AppCompatActivity {

    RadioButton radbtn1,radbtn2,radbtn3,radbtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_suspicion);
        radbtn1= (RadioButton) findViewById(R.id.radbtn1);
        radbtn2= (RadioButton) findViewById(R.id.radbtn2);
        radbtn3=(RadioButton) findViewById(R.id.radbtn3);
        radbtn4=(RadioButton) findViewById(R.id.radbtn4);
        radbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        radbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        radbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        radbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
