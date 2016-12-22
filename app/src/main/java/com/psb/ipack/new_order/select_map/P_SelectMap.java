package com.psb.ipack.new_order.select_map;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.psb.ipack.new_order.serializ.OrderSerialize;

import java.util.List;

/**
 * Created by mehdi on 12/7/16.
 */

public class P_SelectMap implements iP_SelectMap {

    private Context context;
    private iV_SelectMap ivSelectMap;
    private M_SelectMap mSelectMap;
    private OrderSerialize orderSerialize;

    public P_SelectMap(Context context, iV_SelectMap ivSelectMap, boolean hasEndPoint) {
        setContext(context);
        setOrderSerialize(ivSelectMap.getOrderSerialize());
        this.ivSelectMap = ivSelectMap;
        this.mSelectMap = new M_SelectMap(getContext(), this, hasEndPoint);

    }

    public void setPoint(LatLng latLng) {
        if (mSelectMap.getStartPoint() == null) {//now set start point
            mSelectMap.setStartPoint(latLng);
            if (mSelectMap.hasEndPoint()) {
                ivSelectMap.onDrawStartLocation(latLng, false);
            } else {
                ivSelectMap.onDrawStartLocation(latLng, true);
            }
        } else if (mSelectMap.getEndPoint() == null && mSelectMap.hasEndPoint()) {//now set End point
            mSelectMap.setEndPoint(latLng);
            mSelectMap.doStartGetRoute();
            ivSelectMap.onStartDrawRoute(latLng);
        }
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setOrderSerialize(OrderSerialize orderSerialize) {
        this.orderSerialize=orderSerialize;
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return this.orderSerialize;
    }

    @Override
    public void onSuccessGetRoute(LatLng startPoint,LatLng endPoint,List<LatLng> arrPoints,long distance,long duration) {
        ivSelectMap.onDrawEndLocation(startPoint,endPoint,arrPoints,distance,duration);
    }

    @Override
    public void onFailedGetRoute() {
        ivSelectMap.onFailedDrawRoute();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
    
    public void clickRetryBtn(int progress){
        if(progress==-1){
            mSelectMap.doStartGetRoute();
            ivSelectMap.onRetryDrawRoute();
        }else{
            mSelectMap.restPoints();
            ivSelectMap.onRenewLocation();
        }
        
    }
}
