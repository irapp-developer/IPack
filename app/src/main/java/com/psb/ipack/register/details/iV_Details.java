package com.psb.ipack.register.details;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public interface iV_Details {
    
    Context getContext();
    ParcelableRegister getRegisterParce();
    
    void onStartAttempRegister();
    void onSucceeRegister();
    void onFailedRegister(int errorCode, String errorMessage);
}
