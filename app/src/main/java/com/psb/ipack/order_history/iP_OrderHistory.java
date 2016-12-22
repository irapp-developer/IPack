package com.psb.ipack.order_history;

import android.content.Context;

/**
 * Created by mehdi on 12/11/16.
 */

public interface iP_OrderHistory {
    void setContext(Context context);
    Context getContext();
    void onBindViewHolder(ViewHolder_HistoryList holder,int position);
    int getListCount();
}
