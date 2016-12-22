package com.psb.ipack.register.login;

import android.content.Context;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public class P_Login implements iP_Login {
    private Context context;
    private M_Login mLogin;
    private iV_Login ivLogin;

    public P_Login(iV_Login ivLogin) {
        this.ivLogin = ivLogin;
        setContext(this.ivLogin.getContext());
        mLogin=new M_Login(this);
        
    }
    
    public void attemptLogin(String userName,String password){
        mLogin.doLogin(userName,password);
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void onSuccessLogin(int mode) {
        ivLogin.onSuccessLogin(mode);
    }

    @Override
    public void onFailedLogin(int errorCode, String errorMessage) {
        ivLogin.onFailedLogin(errorCode,errorMessage);
    }

    @Override
    public ParcelableRegister getRegisterParce() {
        return this.ivLogin.getRegisterParce();
    }

}
