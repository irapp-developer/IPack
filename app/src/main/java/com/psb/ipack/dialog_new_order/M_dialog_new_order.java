package com.psb.ipack.dialog_new_order;

import android.content.Context;

import com.psb.ipack.splash.serializ.ResponseCatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/1/16.
 */

public class M_dialog_new_order implements iM_dialog_new_order {
    private Context context;
    private iP_dialog_new_order pNewOrderDialog;
    private ArrayList<ResponseCatData> arrCat;
    private ArrayList<ResponseCatData> arrSubCat;
    private int selectedIndexCat;
    private int selectedIndexSubCat;
    

    public M_dialog_new_order(Context context, iP_dialog_new_order pNewOrderDialog) {
        setContext(context);
        this.pNewOrderDialog = pNewOrderDialog;
    }

    public List<ResponseCatData> getArrCat() {
        if (arrCat==null) {
            arrCat = new ArrayList<>(Db_dialog_new_order.getCatList(getContext()));

        }
        return arrCat;
    }

    public List<ResponseCatData> getArrSubCat(int catIndex) {
        selectedIndexCat=catIndex;
        arrSubCat = new ArrayList<>(Db_dialog_new_order.getSubCatList(getContext(), arrCat.get(catIndex).getId()));
        return arrSubCat;
    }
    
   public void setSelectedIndexSubCat(int subCatIndex){
       this.selectedIndexSubCat=subCatIndex;
   }
    
   public int getSelectedOrderId(){
       if(arrSubCat.size()>0){
           return arrSubCat.get(selectedIndexSubCat).getId();
       }else{
           return arrCat.get(selectedIndexCat).getId();
       }
   } 
    


    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }


}
