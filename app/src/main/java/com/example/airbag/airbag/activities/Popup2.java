package com.example.airbag.airbag.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.classes.Bag;
import com.example.airbag.airbag.fragments.MainFragment;
import com.example.airbag.airbag.models.bagdetailspostmodel.BagDetailsPostModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by risha on 3/30/2017.
 */

public class Popup2 extends Activity {

    String TicketNumber,BagType,BagName,BagColor,BagBrand,mobile;
    EditText et_BagType,et_BagName,et_BagColor,et_BagBrand;
    Button finalSaveButton;
    ImageButton fab;
    List<Bag> bags;
    SharedPreferences shared;
    JSONObject json[];
    int k;
    Bag b;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width),(int)(height));
        bags=new ArrayList<Bag>();
        et_BagType= (EditText) findViewById(R.id.et_BagType);
        et_BagBrand= (EditText) findViewById(R.id.et_BagBrand);
        et_BagName= (EditText) findViewById(R.id.et_BagName);
        et_BagColor= (EditText) findViewById(R.id.et_BagColor);
        fab = (ImageButton) findViewById(R.id.fab1);
        finalSaveButton= (Button) findViewById(R.id.finalbutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BagType=et_BagType.getText().toString();
                BagBrand=et_BagBrand.getText().toString();
                BagName=et_BagName.getText().toString();
                BagColor=et_BagColor.getText().toString();
                bags.add(new Bag(BagColor,BagType,BagName,BagBrand));
                et_BagBrand.getText().clear();
                et_BagColor.getText().clear();
                et_BagType.getText().clear();
                et_BagName.getText().clear();

            }
        });

        finalSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                json=new JSONObject[20];
                k=0;
                BagType=et_BagType.getText().toString();
                BagBrand=et_BagBrand.getText().toString();
                BagName=et_BagName.getText().toString();
                BagColor=et_BagColor.getText().toString();
                bags.add(new Bag(BagColor,BagType,BagName,BagBrand));
                for(int i=0;i<bags.size();i++)
                {
                  b=bags.get(i);
                    json[k]=b.toJSON();
                    k=k+1;
                }
                shared=getSharedPreferences("MyPrefrences",0);
                mobile=shared.getString("mobile","");
                TicketNumber=shared.getString("TicketNumber","");

                postBaghit();
                finish();


            }
        });

    }
    public void postBaghit()
    {
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<BagDetailsPostModel> call = api.postBagDetails(TicketNumber,mobile,json);
        call.enqueue(new Callback<BagDetailsPostModel>() {
            @Override
            public void onResponse(Call<BagDetailsPostModel> call, Response<BagDetailsPostModel> response) {
                Log.e("response",response.toString());
                Log.d("status",response.body().getStatus().toString());
            }

            @Override
            public void onFailure(Call<BagDetailsPostModel> call, Throwable t) {

            }
        });
    }

}

