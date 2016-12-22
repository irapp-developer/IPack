package com.psb.ipack.register;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/12/16.
 */

public class P_Register implements iP_Register{
    private Context context;
    private iV_Register ivRegister;
    private M_Register mRegister;
    

    public P_Register(iV_Register ivRegister) {
        this.ivRegister = ivRegister;
        this.setContext(ivRegister.getContext());
        mRegister=new M_Register(this);
    }
    
    public ParcelableRegister getRegisterPace(){
        return mRegister.getParcelableRegister();
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onSuccessRegister() {
        
    }
    @Override
    public void onFailedRegister(int errorCode, String errorMessage) {
        
    }
}
