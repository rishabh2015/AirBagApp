package com.example.airbag.airbag.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.airbag.airbag.R;

public class PlanATripActivity extends AppCompatActivity {
    Button button;
    String isActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_atrip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button= (Button) findViewById(R.id.planatripButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                SharedPreferences pref= getSharedPreferences("MyPrefrences",0);

               isActive=pref.getString("is_active","");

                if(Integer.valueOf(isActive)==1)
                {
                   Intent intent=new Intent(PlanATripActivity.this,MainActivity.class);

                   startActivity(intent);

                }
               else {

                   Intent intent = new Intent(PlanATripActivity.this, AccountDetails.class);

                   startActivity(intent);
                }
            }
        });
    }


}
