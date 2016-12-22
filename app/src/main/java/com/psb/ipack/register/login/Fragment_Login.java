package com.psb.ipack.register.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.dd.CircularProgressButton;
import com.psb.ipack.R;
import com.psb.ipack.register.ActivityRegister;
import com.psb.ipack.register.frg_feedback;
import com.psb.ipack.register.serialize.ParcelableRegister;


/**
 * Created by mehdi on 12/14/16.
 */

public class Fragment_Login extends Fragment implements iV_Login{
    
    private static final String REGISTER_VALUES="RegisterKey";
    
    private ParcelableRegister registerParce;
    private frg_feedback frgFeedBack;
    private CircularProgressButton atempLogin;
    private P_Login pLogin;
    
    private View pView;

    private AutoCompleteTextView textUserName;
    private AppCompatEditText textPassword;
    
    public Fragment_Login(){
        
        
    }
    public static Fragment_Login newInstance(ParcelableRegister parcelableRegister) {
        Fragment_Login fragment = new Fragment_Login();
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
        if(pView==null){
            pView = inflater.inflate(R.layout.frg_register_login, container, false);
            textUserName=(AutoCompleteTextView) pView.findViewById(R.id.userName);
            textPassword=(AppCompatEditText)pView.findViewById(R.id.userPassword);
            atempLogin=(CircularProgressButton)pView.findViewById(R.id.frg_select_map_btn_retry);
            pLogin=new P_Login(this);
            atempLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pLogin.attemptLogin(textUserName.getText().toString(),textPassword.getText().toString());   
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
//////////////////////// Start Login implements ////////////////////////////////////////////////////    
    @Override
    public void onSuccessLogin(int mode) {
        frgFeedBack.successLogin(mode);
    }

    @Override
    public void onFailedLogin(int errorCode, String errorDesc) {

    }

    @Override
    public ParcelableRegister getRegisterParce() {
        return frgFeedBack.getRegisterParce();
    }
//////////////////////// End Login implements //////////////////////////////////////////////////////
}
