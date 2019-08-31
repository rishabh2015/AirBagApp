package com.example.airbag.airbag.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.airbag.airbag.Adapter.BagAdapter;
import com.example.airbag.airbag.Adapter.TripAdapter;
import com.example.airbag.airbag.R;
import com.example.airbag.airbag.activities.ShowBagDetailsActivity;
import com.example.airbag.airbag.classes.Bag;
import com.example.airbag.airbag.classes.Trip;
import com.example.airbag.airbag.models.mybagsmodel.BagDatum;
import com.example.airbag.airbag.models.mybagsmodel.MyBagsModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;
import com.google.android.gms.appdatasearch.GetRecentContextCall;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by shantanu on 21/3/17.
 */

public class BagsFragment extends Fragment {


    private ListView listView;
    SharedPreferences pref;
    String token_key,mobile,ticket_number,bag_QR,name,type,color,brand,trip;
    int journey_status;
    Bitmap bitmap;


    private List<Bag> bags;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bags_fragment, container, false);
        pref = getContext().getSharedPreferences("MyPrefrences", 0);
        token_key = pref.getString("token", "");
        mobile = pref.getString("mobile", "");
        ticket_number = pref.getString("TicketNumber", "");
        listView = (ListView) view.findViewById(R.id.listViewBags);


        getBags();

        return view;
    }

    private void getBags() {
        final ProgressDialog dialog = ProgressDialog.show(this.getContext(), "Fetching Data", "Please wait...", false, false);
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
                    showList();                }

            }

            @Override
            public void onFailure(Call<MyBagsModel> call, Throwable t) {

            }
        });

    }
    public void showList()
    {
        BagAdapter adap=new BagAdapter(this.getContext(),R.layout.bags_layout_adapter,bags);
        listView.setAdapter(adap);
    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.getContext(), ShowBagDetailsActivity.class);
       // Bag bag = bags.get(position);

        //intent.putExtra(KEY_BAG_ID,bag.getBagId());
        //intent.putExtra(KEY_BAG_COLOR,bag.getColor());
        //intent.putExtra(KEY_BAG_SIZE,bag.getSize());
        //intent.putExtra(KEY_BAG_TYPE,bag.getType());
        startActivity(intent);
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
