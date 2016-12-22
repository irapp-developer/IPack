package com.psb.ipack.order_history;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/11/16.
 */

public class M_OrderHistory implements iM_OrderHistory {
    private Context context;
    private iP_OrderHistory iPOrderHistory;
    private List<OrderSerialize> orderHistory = new ArrayList<>();
    private DB_OrderHistory dbOrderHistory;

    public M_OrderHistory(iP_OrderHistory iPOrderHistory) {
        this.iPOrderHistory = iPOrderHistory;
        setContext(this.iPOrderHistory.getContext());
        dbOrderHistory = new DB_OrderHistory(getContext());
        this.orderHistory = dbOrderHistory.getOrderHistory();
    }

    public boolean getLastOrder(long orderId) {
        OrderSerialize newOrder = dbOrderHistory.getOrder(orderId);
        if (newOrder != null) {
            orderHistory.add(0, newOrder);
            return true;
        } else {
            return false;
        }
    }

    public String getName(int position) {
        return orderHistory.get(position).getFirstName();
    }
    public String getOrderName(int position) {
        return orderHistory.get(position).getOrderName();
    }
    public String getDate(int position) {
        return orderHistory.get(position).getCreateTime();
    }
    public OrderSerialize getOrderSerialize(int position){
        return orderHistory.get(position);
    }
    
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public int listSize() {
        return orderHistory.size();
    }
}
