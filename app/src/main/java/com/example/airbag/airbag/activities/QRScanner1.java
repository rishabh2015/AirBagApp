package com.example.airbag.airbag.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.airbag.airbag.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by risha on 4/1/2017.
 */

public class QRScanner1 extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    private ZXingScannerView mScannerView;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanQR();;
            }
        });

    }
    public void ScanQR()
    {
        mScannerView=new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {
        String r=result.getText().toString();
        Log.d("Inside HandleResult",r);
        SharedPreferences prefs=getSharedPreferences("MyPrefrences",0);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putString("shelfnumberresult",result.toString());
       // AlertDialog alertDialog = new AlertDialog.Builder(
               // QRScanner1.this).create();

        //alertDialog.setTitle("Alert Dialog");
        //alertDialog.setMessage("Yout shelf number"+result.toString());
        //alertDialog.setIcon(R.drawable.lost_suitcase);
        //alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            //public void onClick(DialogInterface dialog, int which) {
            //    Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
           // }
        //});


        //alertDialog.show();


    }
}
