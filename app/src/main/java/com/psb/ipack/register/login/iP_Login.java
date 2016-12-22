package com.psb.ipack.register.login;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public interface iP_Login {
    
    void setContext(Context context);
    Context getContext();
    
    void onSuccessLogin(int mode);
    void onFailedLogin(int errorCode, String errorMessage);

    ParcelableRegister getRegisterParce();
    
        
}
