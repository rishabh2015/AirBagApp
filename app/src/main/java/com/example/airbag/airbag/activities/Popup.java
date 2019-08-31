package com.example.airbag.airbag.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.models.getotpmodel.GetOtpModel;
import com.example.airbag.airbag.models.reportlostmodel.ReportLostModel;
import com.example.airbag.airbag.models.statusupdatemodel.StatusUpdateModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by risha on 3/29/2017.
 */

public class Popup extends Activity {


    Button reportALostBag,SaveStatus,report_suspicion;
    RadioButton rb1,rb2,rb3,rb4;
    String Token_key;
    String result,username;
    int status;
    int shelf_number;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width),(int)(height));
        rb1= (RadioButton) findViewById(R.id.radiobutton1);
        rb2= (RadioButton) findViewById(R.id.radiobutton2);
        rb3= (RadioButton) findViewById(R.id.radiobutton3);
        rb4= (RadioButton) findViewById(R.id.radiobutton4);
        report_suspicion= (Button) findViewById(R.id.ReportaSuspiciousItem);
        SharedPreferences pref=getSharedPreferences("MyPrefrences",0);
        Token_key=pref.getString("token","");
        result=pref.getString("Result","");
        Log.e("result",result);
        username=pref.getString("username","");


        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
                Call<StatusUpdateModel> call = api.statusUpdate(result,1,Token_key);
                call.enqueue(new Callback<StatusUpdateModel>() {
                    @Override
                    public void onResponse(Call<StatusUpdateModel> call, Response<StatusUpdateModel> response) {

                        Intent intent=new Intent(getApplicationContext(),QRScanner.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<StatusUpdateModel> call, Throwable t) {

                    }
                });



            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
                Call<StatusUpdateModel> call=api.statusUpdate(result,2,Token_key);
                call.enqueue(new Callback<StatusUpdateModel>() {
                    @Override
                    public void onResponse(Call<StatusUpdateModel> call, Response<StatusUpdateModel> response) {

                        Intent intent=new Intent(getApplicationContext(),QRScanner.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<StatusUpdateModel> call, Throwable t) {

                    }
                });

            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
                Call<StatusUpdateModel> call=api.statusUpdate(result,3,Token_key);
                call.enqueue(new Callback<StatusUpdateModel>() {
                    @Override
                    public void onResponse(Call<StatusUpdateModel> call, Response<StatusUpdateModel> response) {
                        Intent intent=new Intent(getApplicationContext(),QRScanner.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<StatusUpdateModel> call, Throwable t) {

                    }
                });
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
                Call<StatusUpdateModel> call=api.statusUpdate(result,4,Token_key);
                call.enqueue(new Callback<StatusUpdateModel>() {
                    @Override
                    public void onResponse(Call<StatusUpdateModel> call, Response<StatusUpdateModel> response) {
                        Intent intent=new Intent(getApplicationContext(),QRScanner.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<StatusUpdateModel> call, Throwable t) {

                    }
                });
            }
        });

        reportALostBag= (Button) findViewById(R.id.ReportLostItem);
        reportALostBag.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                                                  Call<ReportLostModel> call = api.reportLost(result, username);
                                                  call.enqueue(new Callback<ReportLostModel>() {

                                                      @Override
                                                      public void onResponse(Call<ReportLostModel> call, Response<ReportLostModel> response) {
                                                          status = 1;//response.body().getStatus();
                                                          shelf_number = response.body().getShelf_Number().intValue();
                                                          SharedPreferences prefs = getSharedPreferences("MyPrefrences", 0);
                                                          SharedPreferences.Editor editor = prefs.edit();
                                                          editor.putInt("status", status);
                                                          editor.putInt("shelf_number", shelf_number);
                                                          editor.commit();
                                                      }

                                                      @Override
                                                      public void onFailure(Call<ReportLostModel> call, Throwable t) {

                                                      }


                                                  });
                                              }
        });

        report_suspicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Popup.this,ReportSuspicion.class);
                startActivity(intent);
            }
        });
    }

}


