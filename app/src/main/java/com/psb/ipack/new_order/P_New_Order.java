package com.psb.ipack.new_order;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.psb.ipack.logic.G;
import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/4/16.
 */

public class P_New_Order implements iP_New_Order{
    private Context context;
    private iV_New_Order iVNewOrder;
    private M_New_Order mNewOrder;
    private int orderId;
    private ViewPager.OnPageChangeListener viewPagerChangeListener;
    private OrderSerialize orderSerialize;
    
    public P_New_Order(iV_New_Order iVNewOrder,int orderId){
        this.iVNewOrder=iVNewOrder;
        this.orderId=orderId;
        this.orderSerialize=iVNewOrder.getOrderSerialize();
        setContext(iVNewOrder.getContext());
        mNewOrder=new M_New_Order(this);
    }
    
    public void grantPermission(){
        this.iVNewOrder.onPermissionGranted();
    }
    
    public boolean hasEndPoint(){
        return mNewOrder.hasEndPoint();
    }
    
    public int getDefaultPrice(){
        return mNewOrder.getDefaultPrice();
    }

    public ViewPager.OnPageChangeListener getPageListner(){
        if(viewPagerChangeListener ==null){
            viewPagerChangeListener =new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    
                }
                @Override
                public void onPageSelected(int position) {
                    mNewOrder.setPageIndex(position);
                    if(position==0){
                        if(mNewOrder.getStartPoint()!=null){
                            if(hasEndPoint() & mNewOrder.getEndPoint()!=null){
                                iVNewOrder.showBottom("بازگشت","ادامه",true); 
                            }else if (!hasEndPoint()) {
                                iVNewOrder.showBottom("بازگشت","ادامه",true);
                            } else{//hide next btn
                                iVNewOrder.showBottom("بازگشت","ادامه",false);
                                Log.d(G.LOG_TAG,"disapear 1");
                            }
                        }else{//hide next btn
                            iVNewOrder.showBottom("بازگشت","ادامه",false);
                            Log.d(G.LOG_TAG,"disapear 2");
                        }
                    }else if (position==1){
                        if(mNewOrder.isDescFiled()){
                            iVNewOrder.showBottom("مرحله قبل","مشاهده و ارسال فاکتور",true);
                        }else{
                            iVNewOrder.showBottom("ثبلی","ادامه",false);    
                        }
                    }
                    else if (position==2){
                        iVNewOrder.showBottom("مرحله قبل","ثبت سفارش",true);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };
            
        }
        return viewPagerChangeListener;
    }
    
    public void goNextPage(){
        if(mNewOrder.getPageIndex()==0){
            iVNewOrder.setPagerPosition(1); 
        }else if(mNewOrder.getPageIndex()==1){
            iVNewOrder.updateFactor();
            iVNewOrder.setPagerPosition(2);
        }else if(mNewOrder.getPageIndex()==2){
            mNewOrder.doSendRequest();
        }
    }
    
    public void goPrevPage(){
        if (mNewOrder.getPageIndex() == 0) {
            iVNewOrder.setPagerPosition(-1);
        } else {
            iVNewOrder.setPagerPosition(mNewOrder.getPageIndex() - 1);
        }
    }
    
    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public int getOrderId() {
        return this.orderId;
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return orderSerialize;
    }

    @Override
    public void successSubmitRequest(long savedOrderId) {
        iVNewOrder.successSubmitRequest(savedOrderId);
    }

    @Override
    public void failedSubmitRequest(int errorCode, String errorMessage) {
        iVNewOrder.failedSubmitRequest(errorCode,errorMessage);
    }

}
