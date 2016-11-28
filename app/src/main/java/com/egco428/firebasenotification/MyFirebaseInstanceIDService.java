package com.egco428.firebasenotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Natamon Tangmo on 24-Nov-16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh(){
        Log.i("Result","onTokenRefresh"+ FirebaseInstanceId.getInstance().getToken());
    }
}
