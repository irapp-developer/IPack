package com.psb.ipack.dialog_new_order;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.psb.ipack.splash.serializ.ResponseCatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/1/16.
 */

public class P_dialog_new_order implements iP_dialog_new_order {
    private Context context;
    private iV_dialog_new_order vDialogNewOrder;
    private M_dialog_new_order mDialogNewOrder;

    public P_dialog_new_order(Context context, iV_dialog_new_order vDialogNewOrder) {
        setContext(context);
        this.vDialogNewOrder = vDialogNewOrder;
        mDialogNewOrder = new M_dialog_new_order(getContext(), this);
    }

    public ArrayAdapter<String> getArrCat() {
        List<ResponseCatData> arrCatData = mDialogNewOrder.getArrCat();
        ArrayList<String> arrCat = new ArrayList<>();
        for (ResponseCatData catData : arrCatData) {
            arrCat.add(catData.getPersianTitle());
        }

        ArrayAdapter adapterCat = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrCat);
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapterCat;
    }

    public ArrayAdapter<String> getArrSubCat(int parentIndex) {
        List<ResponseCatData> arrSubCatData = mDialogNewOrder.getArrSubCat(parentIndex);
        ArrayList<String> arrSubCat = new ArrayList<>();
        for (ResponseCatData subCatData : arrSubCatData) {
            arrSubCat.add(subCatData.getPersianTitle());
        }
        ArrayAdapter adapterSubCat = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrSubCat);
        adapterSubCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapterSubCat;
    }
    
    public void setSelectedSubCat(int selectedSubCatIndex){
        mDialogNewOrder.setSelectedIndexSubCat(selectedSubCatIndex);
    }
    
    public int getSelectedOrderId(){
        return mDialogNewOrder.getSelectedOrderId();
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }
}
