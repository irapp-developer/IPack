package com.psb.ipack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.psb.ipack.dialog_new_order.Dialog_NewOrder;
import com.psb.ipack.logic.G;
import com.psb.ipack.new_order.Activity_new_order;
import com.psb.ipack.new_order.serializ.OrderSerialize;
import com.psb.ipack.order_history.Adapter_OrderHistory;
import com.psb.ipack.order_history.P_OrderHistory;
import com.psb.ipack.order_history.iV_OrderHistory;
import com.psb.ipack.register.ActivityRegister;
import com.psb.ipack.splash.P_userInfo;
import com.psb.ipack.splash.iV_userInfo;

public class MainActivity extends AppCompatActivity implements iV_userInfo,iV_OrderHistory {
    AppCompatButton btnTest;
    private RecyclerView recyclerOrderHistory;
    public static final int REQUEST_NEW_ORDER=135;
    public static final int REQUEST_LOGIN=12;
    private P_OrderHistory pOrderHistory;
    private P_userInfo userInfoPresenter;
    
    private Adapter_OrderHistory adapterHistory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInfoPresenter=new P_userInfo(this,this);
        pOrderHistory=new P_OrderHistory(this);
        
        userInfoPresenter.doSendUserInfo();

        btnTest=(AppCompatButton)findViewById(R.id.btn_test);
        recyclerOrderHistory=(RecyclerView)findViewById(R.id.act_main_history_list_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerOrderHistory.setLayoutManager(manager);
        adapterHistory =new Adapter_OrderHistory(pOrderHistory,null);
        recyclerOrderHistory.setAdapter(adapterHistory);
    }

    
    @Override
    public void successSplash(boolean isLogin) {
        Log.d(G.LOG_TAG,"success splash");
        final Dialog_NewOrder bottomSheetDialog = new Dialog_NewOrder(MainActivity.this);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetDialog.isShowing()){
                    bottomSheetDialog.cancel();
                }else{
                    bottomSheetDialog.show();
                }
            }
        });
        if(!isLogin){
            startActivityForResult(new Intent(this, ActivityRegister.class),REQUEST_LOGIN);
        }
    }

    @Override
    public void failedSplash(int error_code, String error_descr) {
        Log.d(G.LOG_TAG,"failed splash:"+error_descr);
    }

    @Override
    public void setContext(Context context) {
        
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemInserted(int position) {
        adapterHistory.notifyItemInserted(position);
        adapterHistory.notifyItemRangeChanged(position,adapterHistory.getItemCount());
    }

    @Override
    public void onItemRemoved(int position) {

    }

    @Override
    public void onReorder(OrderSerialize orderSerialise) {
        Intent intentNewOrder=new Intent(this, Activity_new_order.class);
        intentNewOrder.putExtra("orderId", orderSerialise.getOrderId());
        intentNewOrder.putExtra("parcelabel", (Parcelable)orderSerialise);
        startActivityForResult(intentNewOrder,MainActivity.REQUEST_NEW_ORDER);
        
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_NEW_ORDER & resultCode== Activity.RESULT_OK){
            pOrderHistory.addNewOrder(data.getLongExtra(Activity_new_order.INSERTED_ORDER_ID_KEY,0));
        }else if(requestCode==REQUEST_LOGIN){
            if(resultCode!=Activity.RESULT_OK){
             finish();   
            }
        }
    }
}
