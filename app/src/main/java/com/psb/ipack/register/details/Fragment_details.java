package com.psb.ipack.register.details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.dd.CircularProgressButton;
import com.psb.ipack.R;
import com.psb.ipack.logic.G;
import com.psb.ipack.register.ActivityRegister;
import com.psb.ipack.register.frg_feedback;
import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public class Fragment_details extends Fragment implements iV_Details {

    private static final String REGISTER_VALUES = "RegisterKey";

    private ParcelableRegister registerParce;
    private frg_feedback frgFeedBack;
    private P_Details pDetails;

    private View pView;
    private AppCompatSpinner spnrProvince;
    private AppCompatSpinner spnrCity;


    private AppCompatEditText txtName;
    private AppCompatEditText txtPhoneNumber;
    private AppCompatEditText txtAddress;
    private AppCompatEditText txtDetails;

    private com.dd.CircularProgressButton pbtndRegister;

    public Fragment_details() {


    }

    public static Fragment_details newInstance(ParcelableRegister parcelableRegister) {
        Fragment_details fragment = new Fragment_details();
        Bundle args = new Bundle();
        args.putParcelable(REGISTER_VALUES, parcelableRegister);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            registerParce = getArguments().getParcelable(REGISTER_VALUES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (pView == null) {
            pView = inflater.inflate(R.layout.frg_register_details, container, false);

            spnrProvince = (AppCompatSpinner) pView.findViewById(R.id.frg_register_details_province);
            spnrCity = (AppCompatSpinner) pView.findViewById(R.id.frg_register_details_city);

            txtName = (AppCompatEditText) pView.findViewById(R.id.frg_register_details_name);
            txtPhoneNumber = (AppCompatEditText) pView.findViewById(R.id.frg_register_details_phone);
            txtAddress = (AppCompatEditText) pView.findViewById(R.id.frg_register_address);
            txtDetails = (AppCompatEditText) pView.findViewById(R.id.frg_register_details);

            pbtndRegister = (CircularProgressButton) pView.findViewById(R.id.frg_register_details_submit);
            pDetails = new P_Details(this);

            spnrProvince.setAdapter(pDetails.getProvinceCityList());
            spnrProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spnrCity.setAdapter(pDetails.getCityList(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            
            spnrCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    pDetails.setCityId(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            


            pbtndRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pDetails.attemptRegister(
                            txtName.getText().toString(),
                            txtPhoneNumber.getText().toString(),
                            txtAddress.getText().toString(),
                            txtDetails.getText().toString());
                }
            });
        }
        return pView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityRegister) {
            frgFeedBack = (frg_feedback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        frgFeedBack = null;

    }


    @Override
    public ParcelableRegister getRegisterParce() {
        return this.registerParce;
    }

    @Override
    public void onStartAttempRegister() {
        //TODO show progress
        Log.d(G.LOG_TAG,"start registration1");
    }

    @Override
    public void onSucceeRegister() {
        frgFeedBack.successLogin(ActivityRegister.LOGIN_MODE_OK);
    }

    @Override
    public void onFailedRegister(int errorCode, String errorMessage) {
        frgFeedBack.onFailedRegisteration(errorCode,errorMessage);
    }
}
