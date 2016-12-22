package com.psb.ipack.splash;

import android.content.Context;

/**
 * Created by mehdi on 11/30/16.
 */

public interface iM_userInfo {
    
    void setContext(Context context);
    Context getContext();
    
    void setIpUserInfo(iP_userInfo ipUserInfo);
   iP_userInfo getIpUserInfo();
    void doGetCity();
   
    
}
