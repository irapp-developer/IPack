package com.psb.ipack.new_order.factor;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/10/16.
 */

public interface iV_Factor {
    
    Context getContext();
    OrderSerialize getOrderSerialize();
}
