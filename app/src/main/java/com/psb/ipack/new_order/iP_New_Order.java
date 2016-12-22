package com.psb.ipack.new_order;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/4/16.
 */

public interface iP_New_Order {
    
    void setContext(Context context);
    Context getContext();
    int getOrderId();
   
    OrderSerialize getOrderSerialize();
    
    void successSubmitRequest(long savedOrderId);
    void failedSubmitRequest(int errorCode,String errorMessage);
    
}
