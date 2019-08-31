package com.example.airbag.airbag.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.airbag.airbag.R;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        imageview= (ImageView) findViewById(R.id.imageView);

          new Handler().postDelayed(new Runnable(){
          @Override
            public void run()
          {

              Intent mainIntent = new Intent(Splash.this,UserOrAuth.class);
              startActivity(mainIntent);
              finish();

          }
        },SPLASH_DISPLAY_LENGTH);


        }

}
