package com.psb.ipack.order_history;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.psb.ipack.logic.G;

/**
 * Created by mehdi on 12/11/16.
 */

public class P_OrderHistory implements iP_OrderHistory {
    private Context context;
    private M_OrderHistory mOrderHistory;
    private iV_OrderHistory ivOrderHistory;

    public P_OrderHistory(iV_OrderHistory ivOrderHistory) {
        this.ivOrderHistory=ivOrderHistory;
        setContext(this.ivOrderHistory.getContext());
        this.mOrderHistory = new M_OrderHistory(this);
    }
    
    public void addNewOrder(long orderId){
        if(mOrderHistory.getLastOrder(orderId)){
            ivOrderHistory.onItemInserted(0);
        }
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onBindViewHolder(ViewHolder_HistoryList holder,final int position) {
        holder.orderName.setText(mOrderHistory.getOrderName(position));
        holder.orderDate.setText(mOrderHistory.getDate(position));
        holder.contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(G.LOG_TAG,"position is :"+position);
                Log.d(G.LOG_TAG,"serial is :"+mOrderHistory.getOrderSerialize(position).getOrderName());
                ivOrderHistory.onReorder(mOrderHistory.getOrderSerialize(position));
            }
        });
    }

    @Override
    public int getListCount() {
        return this.mOrderHistory.listSize();
    }
}
