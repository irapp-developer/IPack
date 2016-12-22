package com.psb.ipack.register.details;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public interface iP_Details {
    
    void setContext(Context context);
    Context getContext();

    ParcelableRegister getRegisterParce();
    
    void onStartSubmitRegister();
    void onSuccessRegistration();
    void onFailedRegistration(int errorCode, String errorMessage);
    
    
}
