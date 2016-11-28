package com.egco428.firebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Natamon Tangmo on 24-Nov-16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        for(String k : remoteMessage.getData().keySet()){
            String s = remoteMessage.getData().get(k);
            Log.i("Result","onMessage"+s);
        }

        if(remoteMessage.getNotification() != null){
            String body = remoteMessage.getNotification().getBody();
            String title = remoteMessage.getNotification().getTitle();

            Log.i("Result","onMessageReceived" + body);
            sendNotification(body,title);
        }
    }

    public void sendNotification(String body,String title){
        Intent callIntent = new Intent(this, CallActivity.class);
        PendingIntent cIntent = PendingIntent.getActivity(this,(int)System.currentTimeMillis(),callIntent,0);

        Intent settingIntent = new Intent(this, SettingActivity.class);
        PendingIntent sIntent = PendingIntent.getActivity(this,(int)System.currentTimeMillis(),settingIntent,0);

        Intent warningIntent = new Intent(this, WarningActivity.class);
        PendingIntent wIntent = PendingIntent.getActivity(this,(int)System.currentTimeMillis(),warningIntent,0);

        NotificationCompat.Action callAction = new NotificationCompat.Action.Builder(R.drawable.ic_call,"Call",cIntent).build();
        NotificationCompat.Action settingAction = new NotificationCompat.Action.Builder(R.drawable.ic_setting,"Setting",sIntent).build();
        NotificationCompat.Action warningAction = new NotificationCompat.Action.Builder(R.drawable.ic_warning,"Warning",wIntent).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_message);
        builder.setContentTitle("New Message from " + title);
        builder.setContentText(body);
        builder.addAction(callAction);
        builder.addAction(settingAction);
        builder.addAction(warningAction);
        builder.setWhen(System.currentTimeMillis()+10);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
