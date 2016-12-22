package com.psb.ipack.register;


import com.psb.ipack.register.serialize.ParcelableRegister;

/**
 * Created by mehdi on 12/14/16.
 */

public interface frg_feedback {
    
    ParcelableRegister getRegisterParce();
    void successLogin(int mode);
    void onFailedRegisteration(int errorCode, String errorMessage);
    
}
