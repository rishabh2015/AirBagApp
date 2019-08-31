package com.example.airbag.airbag.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetOtpActivity extends AppCompatActivity {
    String mobileNumber;
    EditText etMobileNumber;
    private ProgressBar spinner;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);
        spinner= (ProgressBar) findViewById(R.id.GetOtp_Progressbar);
        spinner.setVisibility(View.GONE);

        etMobileNumber =(EditText)findViewById(R.id.mobile_number);
        Button btGetOtp = (Button) findViewById(R.id.bt_get_otp);
        btGetOtp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                spinner.setVisibility(View.VISIBLE);

               mobileNumber = etMobileNumber.getText().toString().trim();
               Log.d("Mob",mobileNumber);

                postOtpFromServer();
            }
        });
    }
    public void postOtpFromServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetOtpModel> call = apiInterface.getOTP(mobileNumber);
        call.enqueue(new Callback<GetOtpModel>() {

            @Override
            public void onResponse(Call<GetOtpModel> call, Response<GetOtpModel> response) {
                spinner.setVisibility(View.GONE);
                Intent intent=new Intent(GetOtpActivity.this,VerifyOTPActivity.class);
                intent.putExtra("mobile",mobileNumber);
                if(response.body().getStatus().intValue()==1)
                startActivity(intent);
                else
                    Toast.makeText(getApplicationContext(),"Api inactive",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<GetOtpModel> call, Throwable t) {

                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Toast.makeText(getApplicationContext(),"OnFailure",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
