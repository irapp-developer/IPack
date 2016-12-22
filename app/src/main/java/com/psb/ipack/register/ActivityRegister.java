package com.psb.ipack.register;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.psb.ipack.R;
import com.psb.ipack.logic.G;
import com.psb.ipack.register.details.Fragment_details;
import com.psb.ipack.register.login.Fragment_Login;
import com.psb.ipack.register.serialize.ParcelableRegister;

import java.util.ArrayList;
import java.util.List;

public class ActivityRegister extends AppCompatActivity implements iV_Register,frg_feedback{

    public static final int LOGIN_MODE_OK=1;
    public static final int LOGIN_MODE_REGISTER=2;
    public static final int NUM_VIEWPAGER_COUNT=2;
    
    private P_Register pRegister;
    private ViewPager viewPager;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pRegister=new P_Register(this);
        viewPager=(ViewPager)findViewById(R.id.act_register_pager);
        viewPager.setAdapter(new PagerAdapterRegister(getSupportFragmentManager()));
    }

    @Override
    public Context getContext() {
        return this;
    }

    ////////////// start implements fragment feedback //////////////////////////////////////////////
    @Override
    public ParcelableRegister getRegisterParce() {
        return pRegister.getRegisterPace();
    }

    @Override
    public void successLogin(int mode) {
        if(mode==LOGIN_MODE_REGISTER){
            viewPager.setCurrentItem(1);    
        }else{
            //TODO go start Activity
            Gson gson = new Gson();
            Log.d(G.LOG_TAG, "gson is :" + gson.toJson(getRegisterParce()));
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    @Override
    public void onFailedRegisteration(int errorCode, String errorMessage) {
        
    }
    ////////////// End implements fragment feedback ////////////////////////////////////////////////

    public class PagerAdapterRegister extends FragmentPagerAdapter {

        private List<Fragment> arrFragments = new ArrayList<>();

        public PagerAdapterRegister(FragmentManager fragmentManager) {
            super(fragmentManager);
            for (int i = 0; i < NUM_VIEWPAGER_COUNT; i++) {
                arrFragments.add(null);
            }
        }

        @Override
        public int getCount() {
            return NUM_VIEWPAGER_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if (arrFragments.get(position) == null) {
                switch (position) {
                    case 0: // Fragment # 0 - This will show FirstFragment
                        arrFragments.add(position, Fragment_Login.newInstance(pRegister.getRegisterPace()));
                        break;
                    case 1: // Fragment # 0 - This will show FirstFragment
                        arrFragments.add(position, Fragment_details.newInstance(pRegister.getRegisterPace()));
                        break;
                }
            }
            return arrFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
           /* if(object instanceof Frg_Factor){
                ((Frg_Factor)arrFragments.get(2)).updateViews();
            }*/
            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }
}
