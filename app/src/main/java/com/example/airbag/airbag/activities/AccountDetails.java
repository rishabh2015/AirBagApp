package com.example.airbag.airbag.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.models.accountdetailsmodel.AccountDetailsModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetails extends AppCompatActivity {

    Button btSaveprofile;
    EditText et_username,et_email,et_password;
    String email,username,mobile,password,token_key;
    ProgressBar spinner;
    String reg_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        btSaveprofile=(Button)findViewById(R.id.bt_save_profile);
        et_email= (EditText) findViewById(R.id.et_email);
        et_username= (EditText) findViewById(R.id.et_username);
        spinner= (ProgressBar) findViewById(R.id.AccountDetailsProgressBar);
        spinner.setVisibility(View.GONE);

        et_password= (EditText) findViewById(R.id.et_password);
        btSaveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                spinner.setVisibility(View.VISIBLE);
                email=et_email.getText().toString();
                username=et_username.getText().toString();
                password=et_password.getText().toString();
                reg_id= FirebaseInstanceId.getInstance().getToken();
                SharedPreferences pref= getSharedPreferences("MyPrefrences",0);
                token_key=pref.getString("token","");
                mobile=pref.getString("mobile","");
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("username",username);
                editor.commit();
                postAccountDetails();

            }
        });

    }
    public void postAccountDetails()
    {
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<AccountDetailsModel> call=api.updateProfile(username,password,email,mobile,token_key,reg_id);
        call.enqueue(new Callback<AccountDetailsModel>()
        {

            @Override
            public void onResponse(Call<AccountDetailsModel> call, Response<AccountDetailsModel> response) {
                Log.e("status",response.body().getStatus().toString());
                if(response.body().getStatus().intValue()==1)
                {   spinner.setVisibility(View.GONE);
                    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    Intent intent=new Intent(AccountDetails.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    Toast.makeText(getApplicationContext(),"api not working",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AccountDetailsModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
