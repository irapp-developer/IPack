package com.psb.ipack.new_order.select_map;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.psb.ipack.new_order.serializ.OrderSerialize;

import java.util.List;

/**
 * Created by mehdi on 12/7/16.
 */

public interface iP_SelectMap {
    
    void onSuccessGetRoute(LatLng startPoint,LatLng endPoint,List<LatLng> arrPoints,long distance,long duration);
    void onFailedGetRoute();
    void setContext(Context context);
    Context getContext();
    void setOrderSerialize(OrderSerialize orderSerialize);
    OrderSerialize getOrderSerialize();
}
