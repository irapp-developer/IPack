package com.psb.ipack.new_order;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/4/16.
 */

public interface iM_New_Order {
    
    void setContext(Context context);
    Context getContext();
    
    void setOrderSerialize(OrderSerialize orderSerialize);
}
