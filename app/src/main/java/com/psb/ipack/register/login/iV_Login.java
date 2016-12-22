package com.psb.ipack.register.login;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public interface iV_Login {
    
    Context getContext();
    
    void onSuccessLogin(int mode);
    void onFailedLogin(int errorCode, String errorDesc);
    
    ParcelableRegister getRegisterParce();
}
