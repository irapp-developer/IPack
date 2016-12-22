package com.psb.ipack.logic;

import android.content.Context;
import android.provider.Settings;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mehdi on 11/30/16.
 */

public class Logic {
    
    public static Retrofit getClient(){
        if(G.cliendRetrofit==null) {
            G.cliendRetrofit = new Retrofit.Builder()
                    .baseUrl(G.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return G.cliendRetrofit;
            
        }else{
            return G.cliendRetrofit;
        }
        
    }

    public static String getSecurityCode(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public static void getSecurityCode(){
        
    }
    
}
