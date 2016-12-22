package com.psb.ipack.order_history;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/11/16.
 */

public interface iV_OrderHistory {
    void setContext(Context context);
    Context getContext();
    
    void onItemInserted(int position);
    void onItemRemoved(int position);
    void onReorder(OrderSerialize orderSerialise);
}
