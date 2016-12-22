package com.psb.ipack.order_history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psb.ipack.R;


public class Adapter_OrderHistory extends RecyclerView.Adapter<ViewHolder_HistoryList> {
    private Context mContext;
    private P_OrderHistory pOrderHistory;
    private TextView hint;


    public Adapter_OrderHistory(P_OrderHistory pOrderHistory, TextView hint) {
        this.hint = hint;
        this.pOrderHistory = pOrderHistory;
        mContext = pOrderHistory.getContext();
    }

    @Override
    public int getItemCount() {
        return pOrderHistory.getListCount();
    }


    @Override
    public ViewHolder_HistoryList onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.row_order_history, parent, false);
        ViewHolder_HistoryList viewHolder = new ViewHolder_HistoryList(contactView, viewType);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder_HistoryList holder, int position) {
        pOrderHistory.onBindViewHolder(holder, position);
    }

}
