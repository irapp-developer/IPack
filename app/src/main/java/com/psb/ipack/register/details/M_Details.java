package com.psb.ipack.register.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Logic;
import com.psb.ipack.register.serialize.ModelRegister;
import com.psb.ipack.register.serialize.ParcelableRegister;
import com.psb.ipack.splash.serializ.City;
import com.psb.ipack.splash.serializ.DataCity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static android.content.ContentValues.TAG;


public class M_Details implements iM_Details {
    private Context context;
    private iP_Details ipDetails;
    private ParcelableRegister parceRegister;
    private Db_details dbDetails;

    private List<DataCity> arrProvinceCity = null;
    private List<City> arrCity = null;

    private int selectedCityId;
    
    public M_Details(iP_Details ipDetails) {
        this.ipDetails = ipDetails;
        setContext(ipDetails.getContext());
        this.parceRegister = ipDetails.getRegisterParce();
        dbDetails = new Db_details(this.getContext());
    }
    //================ start province and sub cat ======================================================
    public List<String> getProvinceCityList() {
        List<String> resultDataCity = new ArrayList<>();
        if (arrProvinceCity == null) {
            arrProvinceCity = dbDetails.getProvinceList();
        }
        for (DataCity dataCity : arrProvinceCity) {
            resultDataCity.add(dataCity.getProvinceName());
        }
        selectedCityId = -1;
        return resultDataCity;
    }

    public List<String> getCityList(int provinceIndex) {
        if(arrCity !=null)arrCity.clear();
        arrCity = dbDetails.getCityList(arrProvinceCity.get(provinceIndex).getProvinceId());
        parceRegister.setCityId(arrCity.get(0).getCityId());
        List<String> resultCityList = new ArrayList<>();
        for (City city : arrCity) {
            resultCityList.add(city.getCityName());
        }
        return resultCityList;
    }

    public void setCityId(int cityIndex) {
        parceRegister.setCityId(arrCity.get(cityIndex).getCityId());
    }

    //================ end province and sub cat ========================================================   
    public void doRegister(String name, String phoneNumber, String address, String details) {
        details = (details == null ? "" : details);
        
        ipDetails.onStartSubmitRegister();
        this.parceRegister.setFirstName(name);
        this.parceRegister.setLastName(name);
        this.parceRegister.setPhoneNumber(phoneNumber);
        this.parceRegister.setAddress(address);
        this.parceRegister.setDescription(details);
        Log.d(G.LOG_TAG,"start registration");
        InterfaceRetroRegister apiService = Logic.getClient().create(InterfaceRetroRegister.class);
        Call<ModelRegister> call = apiService.requestRegister(this.parceRegister);
        call.enqueue(new Callback<ModelRegister>() {
            @Override
            public void onResponse(Call<ModelRegister> call, Response<ModelRegister> response) {
                if(response.code()==200){
                    Log.d(G.LOG_TAG,"login is :"+response.body().getStatus());
                    Log.d(G.LOG_TAG,"login is :"+response.body().getMessage());
                    if(response.body().getIsOK()) {
                        ipDetails.onSuccessRegistration();
                        setUserLogined(parceRegister.getUserName());
                    }else{
                        ipDetails.onFailedRegistration(response.body().getStatus(),response.body().getMessage());
                    }
                }else{
                    try {
                        Log.e(G.LOG_TAG,"error is :"+response.errorBody().string());
                        ipDetails.onFailedRegistration(400,"خطا در سرور مجدادا تلاش نمایید");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ModelRegister> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                ipDetails.onFailedRegistration(401,"خطا در ارتباط با اینترنت");
            }
        });
    }

    private void setUserLogined(String userName) {
        SharedPreferences sharedPrefrece = getContext().getSharedPreferences(G.SH_P_USER, getContext().MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefereceEditor = sharedPrefrece.edit();
        sharedPrefereceEditor.putString(G.SH_P_USER_LOGIN, userName);
        sharedPrefereceEditor.commit();
    }
    
    public ParcelableRegister getParceRegister(){
        return this.parceRegister;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }
    
    //----- start interface retrofit ----------------------------------
    private interface InterfaceRetroRegister {
        @Headers("Content-Type: application/json")
        @POST("Service/RegisterCustomerUser")
        Call<ModelRegister> requestRegister(@Body ParcelableRegister paramsRegister);
    }
    //------- end interFace retrofit ----------------------------------

}
