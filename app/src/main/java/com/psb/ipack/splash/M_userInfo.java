package com.psb.ipack.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Logic;
import com.psb.ipack.splash.serializ.ParamsCat;
import com.psb.ipack.splash.serializ.ParamsCity;
import com.psb.ipack.splash.serializ.ParamsDeviceInfo;
import com.psb.ipack.splash.serializ.ParamsLocation;
import com.psb.ipack.splash.serializ.ResponseCat;
import com.psb.ipack.splash.serializ.ResponseCity;
import com.psb.ipack.splash.serializ.ResponseDeviceInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static android.content.ContentValues.TAG;

/**
 * Created by mehdi on 11/30/16.
 */

public class M_userInfo implements iM_userInfo {
    
    private Context context;
    private iP_userInfo iPUserInfo;

    public M_userInfo(Context context, iP_userInfo iPUserInfo) {
        setContext(context);
        setIpUserInfo(iPUserInfo);

    }

    public void doSendUserInfo() {
        userInfoInterface apiService = Logic.getClient().create(userInfoInterface.class);

        //TODO set user current location
        ParamsLocation paramsLocation = new ParamsLocation(1.0, 1.0);

        ParamsDeviceInfo deviceInfoParams = new ParamsDeviceInfo(
                Logic.getSecurityCode(getContext()),
                "0",
                G.USER_MODE_CUSTOMER,
                paramsLocation
        );

        Call<ResponseDeviceInfo> call = apiService.deviceInfo(deviceInfoParams);

        call.enqueue(new Callback<ResponseDeviceInfo>() {
            @Override
            public void onResponse(Call<ResponseDeviceInfo> call, Response<ResponseDeviceInfo> response) {
                setUserSubmitted();
                getIpUserInfo().sentUserInfo(response.body().getStatus(), response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseDeviceInfo> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                getIpUserInfo().sentUserInfo(-1, t.getMessage());
            }
        });
    }

    public boolean getUserSubmittedBefore() {
        SharedPreferences sharedPrefrece = getContext().getSharedPreferences(G.SH_P_CONFIG, getContext().MODE_PRIVATE);
        return sharedPrefrece.getBoolean("userInfo", false);
    }

    public void setUserSubmitted() {
        SharedPreferences sharedPrefrece = getContext().getSharedPreferences(G.SH_P_CONFIG, getContext().MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefereceEditor = sharedPrefrece.edit();
        sharedPrefereceEditor.putBoolean("userInfo", true);
        sharedPrefereceEditor.commit();
    }

    public void doGetCat() {

        userInfoInterface apiService = Logic.getClient().create(userInfoInterface.class);

        ParamsCat paramsCat = new ParamsCat(Logic.getSecurityCode(getContext()), 2, Db_DeviceInfo.getLastCatModifyTime(getContext()));

        Call<ResponseCat> call = apiService.cat(paramsCat);

        call.enqueue(new Callback<ResponseCat>() {
            @Override
            public void onResponse(Call<ResponseCat> call, Response<ResponseCat> response) {
                //---- save or update recieve values -----------
                if (response.body().getStatus() == 0 & response.body().getData().size() > 0) {
                    Db_DeviceInfo.updateCats(getContext(), response.body().getData());
                }
                String message = "";
                if (response.body().getMessage() instanceof String)
                    message = (String) response.body().getMessage();
                getIpUserInfo().gotCatFromInternet(response.body().getStatus(), message);
            }

            @Override
            public void onFailure(Call<ResponseCat> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                getIpUserInfo().gotCatFromInternet(-1, "خطا در برقراری اینترنت");
            }
        });

    }

    public int getCatCount() {
        return Db_DeviceInfo.getCatCount(getContext());
    }
    
    public int getCityCount(){ 
        return Db_DeviceInfo.getCityCount(getContext());
    }

    public boolean getUserLogin() {
        SharedPreferences sharedPrefrece = getContext().getSharedPreferences(G.SH_P_USER, getContext().MODE_PRIVATE);
        return ("-1".equals(sharedPrefrece.getString(G.SH_P_USER_LOGIN,"-1"))?false:true);
    }

    //--------- start setter and getter -------------------------------

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void setIpUserInfo(iP_userInfo ipUserInfo) {
        this.iPUserInfo = ipUserInfo;
    }

    @Override
    public iP_userInfo getIpUserInfo() {
        return this.iPUserInfo;
    }

    @Override
    public void doGetCity() {
            Log.d(G.LOG_TAG,"suxxxxx1");
            userInfoInterface apiService = Logic.getClient().create(userInfoInterface.class);
            ParamsCity paramsCity = new ParamsCity(Logic.getSecurityCode(getContext()), "0");
            Call<ResponseCity> call = apiService.city(paramsCity);
            call.enqueue(new Callback<ResponseCity>() {
                @Override
                public void onResponse(Call<ResponseCity> call, Response<ResponseCity> response) {
                   
                        Log.d(G.LOG_TAG,"get city is :"+response.body().getMessage());
                   
                    //---- save or update recieve values -----------
                    if (response.body().getStatus() == 0 & response.body().getData().size() > 0) {
                        Db_DeviceInfo.saveCity(getContext(),response.body().getData());
                        iPUserInfo.onSuccessGetCity();
                    } else {
                        iPUserInfo.onFailedgetCity();
                    }
                }

                @Override
                public void onFailure(Call<ResponseCity> call, Throwable t) {
                    // Log error here since request failed
                    getIpUserInfo().onFailedgetCity();
                    Log.e(TAG, t.toString());

                }
            });
        }
    //----------- end setter and getter -------------------------------
    //----- start interFace retrofit ----------------------------------
    private interface userInfoInterface {
        @Headers("Content-Type: application/json")
        @POST("Service/SetDeviceInfo")
        Call<ResponseDeviceInfo> deviceInfo(@Body ParamsDeviceInfo paramsDeviceInfo);

        @POST("Service/GetServiceCategories")
        Call<ResponseCat> cat(@Body ParamsCat paramsCat);


        @POST("Service/GetCityProvince")
        Call<ResponseCity> city(@Body ParamsCity paramsCity);

    }
    //------- end interFace retrofit ----------------------------------
}
