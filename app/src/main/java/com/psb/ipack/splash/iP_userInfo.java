package com.psb.ipack.splash;

import android.content.Context;

/**
 * Created by mehdi on 11/30/16.
 */

public interface iP_userInfo {

    void setContext(Context context);

    Context getContext();

    void setUserInfoView(iV_userInfo userInfoView);

    iV_userInfo getUserInfoView();
    
    void onSuccessGetCity();
    void onFailedgetCity();

    void sentUserInfo(int status, String message);

    void gotCatFromInternet(int status, String message);

}
