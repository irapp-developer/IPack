package com.psb.ipack.fcm_notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.psb.ipack.logic.G;

/**
 * Created by mehdi on 12/1/16.
 */

public class ServiceRefreshToken extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        final  String token= FirebaseInstanceId.getInstance().getToken();
        Log.d(G.LOG_TAG,"get fcm token is :\n"+token);
    }
}
