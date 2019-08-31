package com.example.airbag.airbag.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.fragments.BagsFragment;

public class ShowBagDetailsActivity extends AppCompatActivity {


    private TextView textViewBagId;
    private TextView textViewBagColor;
    private TextView textViewBagSize;
    private TextView textViewBagType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bag_details);
        textViewBagId = (TextView) findViewById(R.id.textViewBagId);
        textViewBagColor = (TextView) findViewById(R.id.textViewBagColor);
        textViewBagSize = (TextView) findViewById(R.id.textViewBagSize);
        textViewBagType = (TextView) findViewById(R.id.textViewBagType);

        Intent intent = getIntent();




    }

}
