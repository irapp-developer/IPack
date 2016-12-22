package com.psb.ipack.register.details;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public class P_Details implements iP_Details {
    private Context contex;
    private iV_Details ivDetails;
    private M_Details mDetails;

    public P_Details(iV_Details ivDetails) {
        this.ivDetails = ivDetails;
        setContext(ivDetails.getContext());
        mDetails = new M_Details(this);
    }
//================ start province and sub cat ======================================================
    public ArrayAdapter getProvinceCityList() {
        ArrayAdapter adapterProvinceCity = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,  mDetails.getProvinceCityList());
        return adapterProvinceCity;
    }

    public ArrayAdapter getCityList(int provinceIndex) {
        ArrayAdapter adapterCity = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,  mDetails.getCityList(provinceIndex));
        return adapterCity;
    }

    public void setCityId(int cityIndex) {
        mDetails.setCityId(cityIndex);
    }
//================ end province and sub cat ========================================================

    public void attemptRegister(String name, String phoneNumber, String address, String details) {
        mDetails.doRegister(name, phoneNumber, address, details);
    }

    @Override
    public void setContext(Context context) {
        this.contex = context;
    }

    @Override
    public Context getContext() {
        return this.contex;
    }

    @Override
    public ParcelableRegister getRegisterParce() {
        return ivDetails.getRegisterParce();
    }

    @Override
    public void onStartSubmitRegister() {
        ivDetails.onStartAttempRegister();
    }

    @Override
    public void onSuccessRegistration() {
        ivDetails.onSucceeRegister();
    }

    @Override
    public void onFailedRegistration(int errorCode, String errorMessage) {
        ivDetails.onFailedRegister(errorCode, errorMessage);
    }
}
