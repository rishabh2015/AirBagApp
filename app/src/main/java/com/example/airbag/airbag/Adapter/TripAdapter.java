package com.example.airbag.airbag.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.classes.Trip;

import java.util.List;

/**
 * Created by risha on 3/29/2017.
 */

public class TripAdapter extends ArrayAdapter<Trip> {
    int resource;
    String response;
    Context context;
    public TripAdapter(Context context, int resource, List<Trip> trips)
    {
        super(context,resource,trips);
        this.resource=resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LinearLayout tripsView;
        Trip trip=getItem(position);

        if(convertView==null)
        {
            tripsView=new LinearLayout(getContext());
            String inflater=Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi=(LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource,tripsView,true);

        }
        else
        {
            tripsView=(LinearLayout)convertView;

        }
        TextView tv_ticketNumber= (TextView) tripsView.findViewById(R.id.tv1);
        tv_ticketNumber.setTypeface(null, Typeface.BOLD);
        TextView tv_status= (TextView) tripsView.findViewById(R.id.th2);
        tv_status.setTypeface(null,Typeface.BOLD);
        tv_status.setText(trip.getTripstatus());
        tv_ticketNumber.setText(trip.getTicketNumber());
        return tripsView;
    }
}
