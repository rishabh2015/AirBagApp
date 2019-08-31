package com.example.airbag.airbag.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.classes.Bag;
import com.example.airbag.airbag.classes.Trip;

import java.util.List;

/**
 * Created by risha on 4/1/2017.
 */

public class BagAdapter extends ArrayAdapter<Bag> {
    int resource;
    String response;
    Context context;
    List<Bag> bags;
    public BagAdapter(Context context, int resource, List<Bag> bags)
    {
        super(context,resource,bags);
        this.bags=bags;
        this.resource=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout bagsView;
        Bag bag=getItem(position);
        if(convertView==null)
        {
            bagsView=new LinearLayout(getContext());
            String inflater=Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi=(LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource,bagsView,true);

        }
        else
        {
            bagsView=(LinearLayout)convertView;

        }
        ImageView imageview= (ImageView) bagsView.findViewById(R.id.img2);
        imageview.setImageBitmap(bags.get(position).bitmap);
        TextView tv_ticketNumber= (TextView) bagsView.findViewById(R.id.tv1);
        tv_ticketNumber.setText(bags.get(position).BagName);
        TextView tv_status= (TextView) bagsView.findViewById(R.id.th2);
        tv_status.setTypeface(null,Typeface.BOLD);
        tv_status.setText(bags.get(position).Bagstatus);

        return bagsView;
    }

}
