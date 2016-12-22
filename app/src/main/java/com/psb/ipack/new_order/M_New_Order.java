package com.psb.ipack.new_order;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Logic;
import com.psb.ipack.new_order.serializ.OrderSerialize;
import com.psb.ipack.new_order.serializ.ResponseSubmitRequest;
import com.psb.ipack.splash.serializ.ResponseCatData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static android.content.ContentValues.TAG;

/**
 * Created by mehdi on 12/4/16.
 */

public class M_New_Order implements iM_New_Order {

    private Context context;
    private int orderId;
    private ResponseCatData orderDetails;
    private iP_New_Order ipNewOrder;
    private int pageIndex = 0;
    public static final int NUM_VIEWPAGER_COUNT = 3;
    private OrderSerialize orderSerialize;
    
    
    public M_New_Order(iP_New_Order ip_new_order) {
        setContext(ip_new_order.getContext());
        this.ipNewOrder = ip_new_order;
        this.orderSerialize = ip_new_order.getOrderSerialize();
        orderId = this.ipNewOrder.getOrderId();
        orderDetails = Db_new_order.getOrderDetail(getContext(), orderId);
        
            this.orderSerialize.setDefaultPrice(orderDetails.getDefaultPrice());
            this.orderSerialize.setFinalPrice(orderDetails.getDefaultPrice());
        
        this.orderSerialize.setOrderId(orderId);
        this.orderSerialize.setOrderName(orderDetails.getPersianTitle());
        this.orderSerialize.setSecurityCode(Logic.getSecurityCode(getContext()));
        
        //TODO set fcm token from sharedprefrence
        this.orderSerialize.setFcmToken("0");
        this.orderSerialize.setUserName(getUserName());
    }

    public boolean hasEndPoint() {
        return orderDetails.getIsMovable();
    }

    public int getDefaultPrice() {
        return orderDetails.getDefaultPrice();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public boolean isDescFiled() {
        return (orderSerialize.getFirstName().length() > 0 &
                orderSerialize.getPhoneNumber().length() > 0 &
                orderSerialize.getAddress().length() > 0
        ?true:false
        );
    }
    
    public String getUserName(){
        SharedPreferences sharedPrefrece = getContext().getSharedPreferences(G.SH_P_USER, getContext().MODE_PRIVATE);
        return sharedPrefrece.getString(G.SH_P_USER_LOGIN,"-1");
        
    }
    
    public LatLng getStartPoint(){
        return orderSerialize.getStartPoint();
    }
    
    public LatLng getEndPoint(){
        return orderSerialize.getEndPoint();
    }

    public void doSendRequest(){
        
       submitRequestInterface apiService = Logic.getClient().create(submitRequestInterface.class);
        
        Log.d(G.LOG_TAG,"sent to server is :"+new Gson().toJson(this.orderSerialize));
        Call<ResponseSubmitRequest> call = apiService.requestInfo(this.orderSerialize);

        call.enqueue(new Callback<ResponseSubmitRequest>() {
            @Override
            public void onResponse(Call<ResponseSubmitRequest> call, Response<ResponseSubmitRequest> response) {
                if(response.code()==200){
                    Log.d(G.LOG_TAG,"serverId is :"+response.body().isOK);
                    if(response.body().isOK) {
                        Log.d(G.LOG_TAG,"serverId is :"+response.body().data.getServerRequestId());
                        orderSerialize.setCreateTime(response.body().data.getSubmitTime());
                        orderSerialize.setServerRequestId(response.body().data.getServerRequestId());
                        long insertId=Db_new_order.insertNewOrder(getContext(),orderSerialize);
                        if(insertId>0){
                        ipNewOrder.successSubmitRequest(insertId);
                        }else{
                            ipNewOrder.failedSubmitRequest(0,"دهیره نگردید");  
                        }
                    }else{
                        ipNewOrder.failedSubmitRequest(response.body().status,""+response.body().status);
                    }
                }else{
                    try {
                        Log.e(G.LOG_TAG,"error is :"+response.errorBody().string());
                        ipNewOrder.failedSubmitRequest(123,"Error");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSubmitRequest> call, Throwable t) {
                // Log error here since request failed
                ipNewOrder.failedSubmitRequest(123,"Error");
                Log.e(TAG, t.toString());
            }
        });
    }
    
    @Override
    public void setOrderSerialize(OrderSerialize orderSerialize) {

    }

    //----- start interface retrofit ----------------------------------
    private interface submitRequestInterface {
        @Headers("Content-Type: application/json")
        @POST("Service/SubmitCustomerRequest")
        Call<ResponseSubmitRequest> requestInfo(@Body OrderSerialize paramsRequest);
    }
    //------- end interFace retrofit ----------------------------------
}
