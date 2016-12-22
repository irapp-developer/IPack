package com.psb.ipack.register;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/12/16.
 */

public interface iM_Register {

    void setContext(Context context);
    Context getContext();
    ParcelableRegister getParcelableRegister();
    
}
