package com.psb.ipack.fcm_notification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.psb.ipack.logic.G;

public class ServiceGetMessage extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        
        Log.d(G.LOG_TAG,"message is :"+remoteMessage.getData().get("mode"));
    }
}
