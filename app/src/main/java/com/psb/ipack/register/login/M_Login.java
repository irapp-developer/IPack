package com.psb.ipack.register.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Logic;
import com.psb.ipack.register.ActivityRegister;
import com.psb.ipack.register.login.serialize.ParamsLogin;
import com.psb.ipack.register.login.serialize.ResponseLogin;
import com.psb.ipack.register.serialize.ParcelableRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mehdi on 12/14/16.
 */

public class M_Login implements iM_Login {
    private Context context;
    private iP_Login ipLogin;
    private ParcelableRegister registerParce;

    public M_Login(iP_Login ipLogin) {
        this.ipLogin = ipLogin;
        setContext(this.ipLogin.getContext());
        this.registerParce=ipLogin.getRegisterParce();
    }
    
    public void doLogin(final String userName,final String password){
        ParamsLogin paramsLogin=new ParamsLogin(userName,password, Logic.getSecurityCode(getContext()), 2);
        InterfaceRetroLogin apiService = Logic.getClient().create(InterfaceRetroLogin.class);
        Log.d(G.LOG_TAG,"login user is :"+new Gson().toJson(paramsLogin));
        Call<ResponseLogin> call = apiService.login(paramsLogin);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.code()==200){
                    Log.d(G.LOG_TAG,"login is :"+response.body().getStatus());
                    if(response.body().getIsOK() & response.body().getStatus()==0) {
                        registerParce.setUserName(userName);
                        registerParce.setPassword(password);
                        ipLogin.onSuccessLogin(ActivityRegister.LOGIN_MODE_OK);
                        setUserLogined(userName);
                    }else if (response.body().getStatus()==3) {
                        registerParce.setUserName(userName);
                        registerParce.setPassword(password);
                        ipLogin.onSuccessLogin(ActivityRegister.LOGIN_MODE_REGISTER);
                    }else {
                        ipLogin.onFailedLogin(response.body().getStatus(),response.body().getMessage());
                    }
                }else{
                    ipLogin.onFailedLogin(400,"خطا در سرور ٬ مجدادا تلاش نمایید");
                }
            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                // Log error here since request failed
                ipLogin.onFailedLogin(400,"اشکال در ارتباط با اینترنت");
            }
        });
        
    }
    
    private void setUserLogined(String userName) {
        SharedPreferences sharedPrefrece = getContext().getSharedPreferences(G.SH_P_USER, getContext().MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefereceEditor = sharedPrefrece.edit();
        sharedPrefereceEditor.putString(G.SH_P_USER_LOGIN, userName);
        sharedPrefereceEditor.commit();
    }
    
    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    //----- start interface retrofit ----------------------------------
    private interface InterfaceRetroLogin {
        @Headers("Content-Type: application/json")
        @POST("Service/SignIn")
        Call<ResponseLogin> login(@Body ParamsLogin paramsLogin);
    }
    //------- end interFace retrofit ----------------------------------
    
    
}
