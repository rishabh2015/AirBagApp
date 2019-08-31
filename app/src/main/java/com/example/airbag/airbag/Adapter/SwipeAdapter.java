package com.example.airbag.airbag.Adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airbag.airbag.R;

/**
 * Created by risha on 3/27/2017.
 */

public class SwipeAdapter extends PagerAdapter {
    private int[] image_resources={R.drawable.airport,R.drawable.airport2,R.drawable.airport3,R.drawable.airport4};
    private Context ctx;
    private LayoutInflater layoutInflater;
    public SwipeAdapter(Context ctx)
    {
        this.ctx=ctx;
    }


    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
       return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container,int position)
    {
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swipe_image,null);
        ImageView imageView= (ImageView) item_view.findViewById(R.id.imageViewUser);
        imageView.setImageResource(image_resources[position]);
        ViewPager vp=(ViewPager)container;
        vp.addView(item_view,0);
        return item_view;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        ViewPager vp=(ViewPager)container;
        View view=(View)object;
        vp.removeView(view);
    }
}
