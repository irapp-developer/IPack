package com.psb.ipack.order_history;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.psb.ipack.R;


/**
 * Created by mehdi on 12/11/16.
 */

public class ViewHolder_HistoryList extends RecyclerView.ViewHolder{

    View contentView;
    AppCompatTextView orderName;
    AppCompatTextView orderDate;
    public ViewHolder_HistoryList(View itemView, int ViewType) {
        super(itemView);
        contentView=itemView;
        orderName =(AppCompatTextView)itemView.findViewById(R.id.row_order_history_order_name);
        orderDate =(AppCompatTextView)itemView.findViewById(R.id.row_order_history_order_date);
        

    }
}
