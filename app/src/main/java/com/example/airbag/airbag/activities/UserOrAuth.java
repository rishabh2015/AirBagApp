package com.example.airbag.airbag.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;;import com.example.airbag.airbag.Adapter.SwipeAdapter;
import com.example.airbag.airbag.R;

import java.util.Timer;
import java.util.TimerTask;

public class UserOrAuth extends AppCompatActivity {

    ViewPager viewpager;
    SwipeAdapter swipe_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_auth);
        Button b1=(Button)findViewById(R.id.button11);
        Button b2=(Button)findViewById(R.id.button12);
        viewpager=(ViewPager)findViewById(R.id.view_pager);
        swipe_adapter=new SwipeAdapter(this);
        viewpager.setAdapter(swipe_adapter);

        Timer timer=new Timer();
        timer.schedule(new MyTimerTask(),2000,4000);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),GetOtpActivity.class);
                startActivity(intent);
            }

        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent i= new Intent(getApplicationContext(),AuthoritySignInActivity.class);
                startActivity(i);
            }

        } );

    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            UserOrAuth.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewpager.getCurrentItem()==0)
                    {
                        viewpager.setCurrentItem(1);
                    }
                    else if (viewpager.getCurrentItem()==1)
                    {
                        viewpager.setCurrentItem(2);
                    }
                    else if (viewpager.getCurrentItem()==2)
                    {
                        viewpager.setCurrentItem(3);
                    }
                }
            });
        }
    }


}
