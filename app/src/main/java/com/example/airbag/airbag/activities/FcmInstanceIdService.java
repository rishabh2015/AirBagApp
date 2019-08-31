package com.example.airbag.airbag.activities;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by risha on 4/1/2017.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sharedPrefrences=getApplicationContext().getSharedPreferences("FCM_Pref", 0);
        SharedPreferences.Editor editor=sharedPrefrences.edit();
        editor.putString("FCM_Token",recent_token);
        editor.commit();

    }
}
