package com.example.airbag.airbag.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.models.getotpmodel.GetOtpModel;
import com.example.airbag.airbag.models.verifyotpmodel.VerifyOtpModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTPActivity extends AppCompatActivity {

    EditText otp_text;
    String otp;
    String mobile;
    ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        otp_text = (EditText) findViewById(R.id.OTP);
        Button btVerifyOtp = (Button) findViewById(R.id.btn_verify);
        mobile=getIntent().getStringExtra("mobile");
        spinner= (ProgressBar) findViewById(R.id.VerifyOtp_Progressbar);
        spinner.setVisibility(View.GONE);


        btVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp = otp_text.getText().toString().trim();
                Log.d("otp", otp);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                spinner.setVisibility(View.VISIBLE);



             postVerifyOtpFromServer();
            }
        });
    }

    public void postVerifyOtpFromServer() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<VerifyOtpModel> call = apiInterface.verifyOTP(mobile,otp);
        call.enqueue(new Callback<VerifyOtpModel>() {

            @Override
            public void onResponse(Call<VerifyOtpModel> call, Response<VerifyOtpModel> response) {

                Log.e("otp", response.body().getStatus().toString());
                spinner.setVisibility(View.GONE);
                if(response.body().getStatus().intValue()==1) {
                    Log.d("status",response.body().getStatus().toString());
                    Log.d("is_active",response.body().getis_active().toString());
                    SharedPreferences shared=getSharedPreferences("MyPrefrences",0);
                    SharedPreferences.Editor editor=shared.edit();
                    editor.putString("mobile",mobile);
                    editor.putString("token",response.body().getTokenKey());
                    editor.putString("is_active",response.body().getis_active().toString());
                    editor.commit();
                    startActivity(new Intent(VerifyOTPActivity.this, PlanATripActivity.class));
                }
                else
                    Toast.makeText(VerifyOTPActivity.this, "OTP not correct", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<VerifyOtpModel> call, Throwable t) {
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });
    }

}
