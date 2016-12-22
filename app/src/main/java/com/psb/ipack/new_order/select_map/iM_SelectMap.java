package com.psb.ipack.new_order.select_map;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/7/16.
 */

public interface iM_SelectMap {
    void setContext(Context context);
    Context getContext();
    
    OrderSerialize getOrderSerialize();
    void setOrderSerialize(OrderSerialize orderSerialize);
}
