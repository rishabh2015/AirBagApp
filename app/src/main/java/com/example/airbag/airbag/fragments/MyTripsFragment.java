package com.example.airbag.airbag.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.airbag.airbag.Adapter.TripAdapter;
import com.example.airbag.airbag.R;
import com.example.airbag.airbag.activities.BagActivity;
import com.example.airbag.airbag.activities.ShowBagDetailsActivity;
import com.example.airbag.airbag.classes.Trip;
import com.example.airbag.airbag.models.mytripsmodel.MyTripsModel;
import com.example.airbag.airbag.models.mytripsmodel.TripDatum;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shantanu on 21/3/17.
 */

public class MyTripsFragment extends Fragment {
    ListView listview;
    String token_key, mobile;
    String ticket_number, trip_status0;
    int trip_status;
    List<Trip> trips;
    SharedPreferences pref;
    TripAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.my_trips_fragment, container, false);
        pref = getContext().getSharedPreferences("MyPrefrences", 0);
        token_key = pref.getString("token", "");
        mobile = pref.getString("mobile", "");
        listview = (ListView) rootview.findViewById(R.id.list);
        trips = new ArrayList<Trip>();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SharedPreferences pref= getContext().getSharedPreferences("MyPrefrences",0);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("TicketNumber",trips.get(position).getTicketNumber().toString());
                editor.commit();
                Intent intent = new Intent(getActivity(), BagActivity.class);

                startActivity(intent);
            }
        });

        getTrips();
        return rootview;
    }

    private void getTrips() {
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
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<MyTripsModel> call = api.listTrip(token_key, mobile);
        call.enqueue(new Callback<MyTripsModel>() {
            @Override
            public void onResponse(Call<MyTripsModel> call, Response<MyTripsModel> response) {

                List<TripDatum> tripdatum = response.body().getTripData();
                for (TripDatum trip : tripdatum) {
                    ticket_number = trip.getTicketNumber();
                    trip_status = trip.getTripStatus();
                    if (trip_status == 0)
                        trip_status0 = "inactive";
                    else if (trip_status == 1)
                        trip_status0 = "active";
                    else if (trip_status == 2)
                        trip_status0 = "finished";

                    trips.add(new Trip(ticket_number, trip_status0));


                }
                showList();


            }

            @Override
            public void onFailure(Call<MyTripsModel> call, Throwable t) {

            }
        });
    }
    public void showList()
    {

        TripAdapter adap=new TripAdapter(getContext(),R.layout.card_view,trips);
        listview.setAdapter(adap);
    }
}