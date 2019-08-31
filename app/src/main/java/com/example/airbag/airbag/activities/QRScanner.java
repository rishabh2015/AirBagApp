package com.example.airbag.airbag.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.View;

import com.example.airbag.airbag.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

    }
    public void ScanQR(View view)
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
        editor.putString("Result",r);
        editor.commit();
        Intent intent=new Intent(getApplicationContext(), Popup.class);
        startActivity(intent);

    }
}
