package com.psb.ipack.dialog_new_order;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.psb.ipack.MainActivity;
import com.psb.ipack.R;
import com.psb.ipack.new_order.Activity_new_order;

/**
 * Created by mehdi on 12/1/16.
 */

public class Dialog_NewOrder extends BottomSheetDialog implements iV_dialog_new_order {
    P_dialog_new_order presenterNewDialogOrder;
    private Context context;
    private MainActivity mActivity;
    
    public Dialog_NewOrder(@NonNull final Context context) {
        super(context);
        this.context=context;
        if(context instanceof MainActivity){
            mActivity=(MainActivity)context;
        }
        setContentView(R.layout.dialog_new_order);
        BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(findViewById(R.id.RelativeLayoutSheet));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        final AppCompatSpinner spinnerCat=(AppCompatSpinner)findViewById(R.id.dialog_new_order_spinner_Cat);
        final AppCompatSpinner spinnerSubCat=(AppCompatSpinner)findViewById(R.id.dialog_new_order_spinner_SubCat);
        final AppCompatButton btnNewOrder=(AppCompatButton)findViewById(R.id.dialog_new_order_btn_new_order);


        presenterNewDialogOrder =new P_dialog_new_order(context,this);
        spinnerCat.setAdapter(presenterNewDialogOrder.getArrCat());
        spinnerCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> arrSubCat= presenterNewDialogOrder.getArrSubCat(i);
                
                if(arrSubCat.getCount()>0) {
                    spinnerSubCat.setVisibility(View.VISIBLE);
                    spinnerSubCat.setAdapter(arrSubCat);
                }else{
                    spinnerSubCat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSubCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenterNewDialogOrder.setSelectedSubCat(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewOrder=new Intent(context, Activity_new_order.class);
                intentNewOrder.putExtra("orderId", presenterNewDialogOrder.getSelectedOrderId());
                mActivity.startActivityForResult(intentNewOrder,MainActivity.REQUEST_NEW_ORDER);
                cancel();
            }
        });
    }

    public Dialog_NewOrder(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected Dialog_NewOrder(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    
    

}
