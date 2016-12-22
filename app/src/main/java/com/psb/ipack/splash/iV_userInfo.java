package com.psb.ipack.splash;

/**
 * Created by mehdi on 11/30/16.
 */

public interface iV_userInfo {

    void successSplash(boolean isLogin);

    void failedSplash(int error_code, String error_descr);

}
