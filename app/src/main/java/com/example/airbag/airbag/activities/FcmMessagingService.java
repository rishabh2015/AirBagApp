package com.example.airbag.airbag.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.fragments.MyTripsFragment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by risha on 4/1/2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        Map<String, String> message=remoteMessage.getData();
        String message_id=remoteMessage.getMessageId();
        Log.d("messageId",message_id);
        String value[]=new String[20];
        int k=0;
        Set keys=message.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String title = (String) i.next();
            value[k] = (String) message.get(title);
            k=k+1;
            Log.d("message data",title + " = " + value);
        }
        String ticket=value[0];
        String tripId=value[1];
        String status=value[2];
        String shelf_number=value[3];


      Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("ticket"+ticket);
        notificationBuilder.setContentText("tripId:"+tripId+"status"+status+"shelf_number"+shelf_number);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

    }

    public void onReceive(Context context, Intent intent) {
        Log.d("ALERT", "Message Received");

        Intent screen2 = new Intent(context, MyTripsFragment.class);
        screen2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(screen2);
    }
}
