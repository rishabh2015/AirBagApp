package com.example.airbag.airbag.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.airbag.airbag.models.authsigninmodel.AuthoritySignInModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthoritySignInActivity extends AppCompatActivity {

    Button bt_SignIn;
    EditText etusername;
    ProgressBar spinner;
    EditText etpassword;
    String username, password;
    String recent_token;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authority_sign_in);
        bt_SignIn = (Button) findViewById(R.id.SignIn);
        etusername = (EditText) findViewById(R.id.et_userName);
        etpassword = (EditText) findViewById(R.id.et_password);
        spinner = (ProgressBar) findViewById(R.id.AuthSignIn_Progressbar);
        spinner.setVisibility(View.GONE);

        bt_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                spinner.setVisibility(View.VISIBLE);
                username = etusername.getText().toString();
                password = etpassword.getText().toString();
                SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                count = shared.getInt("count",0);
                SharedPreferences.Editor editor = shared.edit();
                if (count == 0) {
                    count = count + 1;
                    editor.putInt("count", count);
                    editor.commit();
                    postAuthoritySignIn();
                } else if (count > 0 && count < 25) {
                    count = count + 1;
                    editor.putInt("count", count);
                    editor.commit();
                    startActivity(new Intent(AuthoritySignInActivity.this, QRScanner.class));

                } else{
                    count=0;
                    editor.putInt("count", count);
                    editor.commit();
                    postAuthoritySignIn();
                }


            }
        });

    }

    public void postAuthoritySignIn() {

           ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

           Call<AuthoritySignInModel> call = api.authSignIn(username, password);


            call.enqueue(new Callback<AuthoritySignInModel>() {
                @Override
               public void onResponse(Call<AuthoritySignInModel> call, Response<AuthoritySignInModel> response) {
                  Log.d("Response", response.toString());
                   if (response.body().getStatus().intValue() == 1) {
                        SharedPreferences prefs = getSharedPreferences("Authdetails", 0);
                       SharedPreferences.Editor editor = prefs.edit();
                       editor.putString("username", username.toString());
                       editor.putString("password", password.toString());
                        editor.putString("token", response.body().getTokenKey().toString());
                        editor.commit();
                        startActivity(new Intent(AuthoritySignInActivity.this, QRScanner.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "wrong password entered", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<AuthoritySignInModel> call, Throwable t) {
                    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    Toast.makeText(AuthoritySignInActivity.this, "Api not active", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }


