package com.example.airbag.airbag.activities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by risha on 4/1/2017.
 */

public class MySingleton {
    private static MySingleton minstance;
    private static Context mctx;
    private RequestQueue requestQueue;
    public MySingleton(Context context)
    {
        mctx=context;
        requestQueue=getRequestQueue();

    }
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        return requestQueue;
    }
    public static synchronized MySingleton getMinstance(Context context)
    {
        if(minstance==null)
        {
            minstance=new MySingleton(context);
        }
        return minstance;
    }
    public<T> void  addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }


}
