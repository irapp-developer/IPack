package com.psb.ipack;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class App extends Application {
    protected static Activity mActivity;
    protected static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void setActivity(Activity activity) {
        mActivity = activity;
    }

    public static Context getContext() {
        return context;
    }

    public static Activity getActivity() {
        return mActivity;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static boolean isOtherActivityVisible() {
        return other_activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    public static void activityDestroy() {
        activityVisible = false;
    }

    public static Boolean setOtherActivityMode(boolean mode) {
        other_activityVisible = mode;
        return other_activityVisible;
    }

    private static boolean activityVisible;
    private static boolean other_activityVisible;

}
