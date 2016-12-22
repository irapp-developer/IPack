package com.psb.ipack.splash;

import android.content.Context;

/**
 * Created by mehdi on 11/30/16.
 */

public class P_userInfo implements iP_userInfo {

    private iV_userInfo userInfoView;
    private M_userInfo userInfoModel;
    private Context context;

    public P_userInfo(Context context, iV_userInfo mUserInfoView) {
        setContext(context);
        setUserInfoView(mUserInfoView);
        userInfoModel = new M_userInfo(getContext(), this);
    }

    public void doSendUserInfo(){
        userInfoModel.doSendUserInfo();
    }
    
    @Override
    public void sentUserInfo(int status, String message) {
        if (status == 0) {//success sent user info
            // start get new cat changes from internet
            userInfoModel.doGetCat();
        } else {//failed send user info
            if (userInfoModel.getUserSubmittedBefore() & 
                    userInfoModel.getCatCount() > 0 &
                    userInfoModel.getCityCount() > 0
                    ) {//submitted before and has cat in db
                getUserInfoView().successSplash(userInfoModel.getUserLogin());
            } else {
                getUserInfoView().failedSplash(1, "خطا در ارتباط با سرور");
            }
        }
    }

    @Override
    public void gotCatFromInternet(int status, String message) {
        if (status == 0 || userInfoModel.getCatCount() > 0) {
            //start get city details
            if(userInfoModel.getCityCount()>0){
            getUserInfoView().successSplash(userInfoModel.getUserLogin());
            }else{
                userInfoModel.doGetCity();
            }
        } else {
            getUserInfoView().failedSplash(1, "خطا در ارتباط با سرور");
        }
    }


    @Override
    public void onSuccessGetCity() {
        getUserInfoView().successSplash(userInfoModel.getUserLogin());
    }

    @Override
    public void onFailedgetCity() {
        getUserInfoView().failedSplash(1, "خطا در ارتباط با سرور");
    }
    

    //------ Start getter and setter -------------
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void setUserInfoView(iV_userInfo userInfoView) {
        this.userInfoView = userInfoView;
    }

    @Override
    public iV_userInfo getUserInfoView() {
        return userInfoView;
    }
    //-------- End getter and setter -------------

}
