package com.psb.ipack.new_order.select_map;

import com.google.android.gms.maps.model.LatLng;
import com.psb.ipack.new_order.serializ.OrderSerialize;

import java.util.List;

/**
 * Created by mehdi on 12/7/16.
 */

public interface iV_SelectMap {
     
    void onStartDrawRoute(LatLng endPoint);
    void onFailedDrawRoute();
    void onRetryDrawRoute();
    void onDrawStartLocation(LatLng latLng, boolean finished);
    void onDrawEndLocation(LatLng startPoint, LatLng endPoint, List<LatLng> arrPoints, long distance, long duration);
    void onRenewLocation(); 
    
    OrderSerialize getOrderSerialize();
    
    
    
}
