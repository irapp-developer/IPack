package com.psb.ipack.new_order;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/4/16.
 */

public interface iV_New_Order {
    
    void setContext(Context context);
    Context getContext();
    void onPermissionGranted();
    void onFailedLoadOrder(); 
    void showBottom(String titleNext,String titlePrev,boolean isShow);
    void setPagerPosition(int index);
    
    void updateFactor();
    OrderSerialize getOrderSerialize();

    void successSubmitRequest(long savedOrderId);
    void failedSubmitRequest(int errorCode,String errorMessage);
    
}
