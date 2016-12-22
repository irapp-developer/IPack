package com.psb.ipack.register;

import android.content.Context;

/**
 * Created by mehdi on 12/12/16.
 */

public interface iP_Register {

    void setContext(Context context);
    Context getContext();
    
    void onSuccessRegister();
    void onFailedRegister(int errorCode, String errorMessage);
    
}
