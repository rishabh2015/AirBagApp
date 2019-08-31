package com.example.airbag.airbag.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.airbag.airbag.Adapter.BagAdapter;
import com.example.airbag.airbag.R;
import com.example.airbag.airbag.classes.Bag;
import com.example.airbag.airbag.models.mybagsmodel.BagDatum;
import com.example.airbag.airbag.models.mybagsmodel.MyBagsModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BagActivity extends AppCompatActivity {

    private ListView listView;
    SharedPreferences pref,prefs;
    String token_key,mobile,ticket_number,bag_QR,name,type,color,brand,trip;
    Integer journey_status;
    Bitmap bitmap;
    private List<Bag> bags;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);
        bags=new ArrayList<Bag>();
        pref = getSharedPreferences("MyPrefrences", 0);
        token_key = pref.getString("token","");
        mobile = pref.getString("mobile","");
        ticket_number = pref.getString("TicketNumber","");
        listView = (ListView) findViewById(R.id.listViewBags);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               prefs=getSharedPreferences("BagDetails",0);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString("Bag Name",bags.get(position).getBagName());
                editor.putString("Bag Brand",bags.get(position).getBagBrand());
                editor.putString("Bag Type",bags.get(position).getBagType());
                editor.putString("Bag Color",bags.get(position).getBagColor());
                editor.commit();
               // Intent intent=new Intent(BagActivity.this,ShowBagDetailsActivity.class);
               // startActivity(intent);

            }
        });


        getBags();

    }

    private void getBags() {
        final ProgressDialog dialog = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        dialog.show();
        long delayInMillis = 5000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, delayInMillis);
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<MyBagsModel> call=api.listBags(token_key,mobile,ticket_number);
        call.enqueue(new Callback<MyBagsModel>() {
            @Override
            public void onResponse(Call<MyBagsModel> call, Response<MyBagsModel> response) {
                List<BagDatum> bagDatum=response.body().getBagData();
                for (BagDatum bag:bagDatum){
                    bag_QR=bag.getQr_code();
                    bag_QR="data:image\\/png;base64,"+bag_QR;
                    String imageDataBytes = bag_QR.substring(bag_QR.indexOf(",")+1);
                    bitmap=ConvertToImage(imageDataBytes);
                    name=bag.getName();
                    type=bag.getType();
                    brand=bag.getBrand();
                    color=bag.getColor();
                    journey_status=bag.getJourney_status();
                    bags.add(new Bag(journey_status,color,type,name,brand,bitmap));

                }

                showList();
            }

            @Override
            public void onFailure(Call<MyBagsModel> call, Throwable t) {

            }
        });

    }
    public void showList()
    {
        BagAdapter adap=new BagAdapter(this,R.layout.bags_layout_adapter,bags);
        listView.setAdapter(adap);
    }

    public Bitmap ConvertToImage(String image){
        try{
            InputStream stream = new ByteArrayInputStream(Base64.decode(image.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            return bitmap;
        }
        catch (Exception e) {
            return null;
        }
    }
}
