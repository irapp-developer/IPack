package com.psb.ipack.logic;

import retrofit2.Retrofit;

/**
 * Created by mehdi on 11/30/16.
 */

public class G {
    public static final String LOG_TAG="ipcak_customers";
    public static final String BASE_URL="http://ipack.persamob.ir/";
    public static final String GOOGLE_MAP_API="http://maps.googleapis.com/";
    public static final String SH_P_CONFIG="share_p_config";
    public static final int DB_VERSION=9;

    public static final String SH_P_USER="share_p_user";
    public static final String SH_P_USER_LOGIN="share_p_user_login";
    
    public static Retrofit cliendRetrofit;
    public static final int USER_MODE_CONTRACTOR=1;
    public static final int USER_MODE_CUSTOMER=2;
    
}
