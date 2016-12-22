package com.psb.ipack.new_order.fill_descr;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/9/16.
 */

public interface iV_fill_descr {
    Context getContext();
    void fillValues(boolean isCompleted);
    OrderSerialize getOrderSerialize();
}
