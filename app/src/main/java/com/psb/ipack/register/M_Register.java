package com.psb.ipack.register;

import android.content.Context;

import com.psb.ipack.logic.Logic;
import com.psb.ipack.register.serialize.Location;
import com.psb.ipack.register.serialize.ModelRegister;
import com.psb.ipack.register.serialize.ParcelableRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mehdi on 12/12/16.
 */

public class M_Register implements iM_Register {
    private Context context;
    private iP_Register ipregister;
    private Db_register dbRegister;
    private ParcelableRegister parcRegister;

    public M_Register(iP_Register ipregister) {
        this.ipregister = ipregister;
        setContext(ipregister.getContext());
        dbRegister = new Db_register(getContext());
        parcRegister=new ParcelableRegister();
        parcRegister.setSecurityCode(Logic.getSecurityCode(getContext()));
        //TODO set real fcm token 
        parcRegister.setFCMToken("1");
        parcRegister.setSecurityCode(Logic.getSecurityCode(getContext()));
        parcRegister.setLocation(new Location(1.0,1.0));
        
        
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public ParcelableRegister getParcelableRegister() {
        return this.parcRegister;
    }
    
    //----- start interface retrofit ----------------------------------
    private interface InterfaceRetroRegister {
        @Headers("Content-Type: application/json")
        @POST("Service/RegisterContractor")
        Call<ModelRegister> requestRegister(@Body ParcelableRegister paramsRegister);
    }
    //------- end interFace retrofit ----------------------------------

}
