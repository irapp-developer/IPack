package com.psb.ipack.new_order.select_map;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.psb.ipack.logic.G;
import com.psb.ipack.new_order.serializ.GeoModel;
import com.psb.ipack.new_order.serializ.OrderSerialize;
import com.psb.ipack.new_order.select_map.serializ.Distance_;
import com.psb.ipack.new_order.select_map.serializ.Duration_;
import com.psb.ipack.new_order.select_map.serializ.EndLocation_;
import com.psb.ipack.new_order.select_map.serializ.ResponseRoute;
import com.psb.ipack.new_order.select_map.serializ.Route;
import com.psb.ipack.new_order.select_map.serializ.RouteDecode;
import com.psb.ipack.new_order.select_map.serializ.StartLocation_;
import com.psb.ipack.new_order.select_map.serializ.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static android.content.ContentValues.TAG;

/**
 * Created by mehdi on 12/7/16.
 */

public class M_SelectMap implements iM_SelectMap {

    private Context context;
    private boolean hasEndPoint;
    private iP_SelectMap ipSelectMap;
    private OrderSerialize orderSerialize;

    public M_SelectMap(Context context, iP_SelectMap ipSelectMap, boolean hasEndPoint) {
        setContext(context);
        setOrderSerialize(ipSelectMap.getOrderSerialize());
        setHasEndPoint(hasEndPoint);
        this.ipSelectMap = ipSelectMap;
    }

    public void doStartGetRoute() {
        Log.d(G.LOG_TAG, "Start get Rout");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(G.GOOGLE_MAP_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        routeInterface apiService = retrofit.create(routeInterface.class);
        Call<ResponseRoute> call = apiService.getRoute(getStartPoint().latitude + "," + getStartPoint().longitude, getEndPoint().latitude + "," + getEndPoint().longitude, false, "metric", "driving");
        call.enqueue(new Callback<ResponseRoute>() {
            @Override
            public void onResponse(Call<ResponseRoute> call, Response<ResponseRoute> response) {
                if(response.code()!=200){
                    ipSelectMap.onFailedGetRoute();
                    return;
                }
                ArrayList<LatLng> routeList = new ArrayList<>();
                long duration=0;
                int distance=0;
                if (response.body().getRoutes().size() > 0) {
                    ArrayList<LatLng> decodelist;
                    Route routeA = response.body().getRoutes().get(0);
                    if (routeA.getLegs().size() > 0) {
                        List<Step> steps = routeA.getLegs().get(0).getSteps();
                        Step step;
                        StartLocation_ StartLocation;
                        EndLocation_ EndLocation;
                        Duration_ stepDuration;
                        Distance_ stepDistance;
                        String polyline;
                        for (int i = 0; i < steps.size(); i++) {
                            step = steps.get(i);
                            StartLocation = step.getStartLocation();
                            routeList.add(new LatLng(StartLocation.getLat(), StartLocation.getLng()));
                            polyline = step.getPolyline().getPoints();
                            decodelist = RouteDecode.decodePoly(polyline);
                            routeList.addAll(decodelist);
                            
                            EndLocation = step.getEndLocation();
                            routeList.add(new LatLng(EndLocation.getLat(), EndLocation.getLng()));

                            stepDuration=step.getDuration();
                            duration+=stepDuration.getValue();
                            
                            stepDistance=step.getDistance();
                            distance+=stepDistance.getValue();
                        }
                    }
                }

                orderSerialize.setSourceGeo(new GeoModel(getStartPoint()));
                orderSerialize.setDestenationGeo(new GeoModel(getEndPoint()));
                
                orderSerialize.setArrRoutes(routeList);
                orderSerialize.setDistance((int)(distance/1000.0f));
                //TODO have to get final price from internet
                orderSerialize.setFinalPrice((int)(orderSerialize.geDefaultPrice()*(distance/1000.0f)));
                
                ipSelectMap.onSuccessGetRoute(getStartPoint(),getEndPoint(),routeList,(int)(distance/1000.0f),duration);
            }

            @Override
            public void onFailure(Call<ResponseRoute> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                ipSelectMap.onFailedGetRoute();

            }
        });

    }

    public boolean hasEndPoint() {
        return hasEndPoint;
    }

    public void setHasEndPoint(boolean hasEndPoint) {
        this.hasEndPoint = hasEndPoint;
    }

    public LatLng getStartPoint() {
        return orderSerialize.getStartPoint();
    }

    public void setStartPoint(LatLng startPoint) 
    {
        orderSerialize.setStartPoint(startPoint);
        orderSerialize.setSourceGeo(new GeoModel(startPoint));
    }

    public LatLng getEndPoint() {
        return orderSerialize.getEndPoint();
    }

    public void setEndPoint(LatLng endPoint) {
        orderSerialize.setEndPoint(endPoint);
    }
    
    public void restPoints(){
        orderSerialize.setStartPoint(null);
        orderSerialize.setEndPoint(null);
        orderSerialize.setArrRoutes(null);
    }
    
    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return orderSerialize;
    }

    @Override
    public void setOrderSerialize(OrderSerialize orderSerialize) {
        this.orderSerialize=orderSerialize;
    }

    //----- start interFace retrofit ----------------------------------
    private interface routeInterface {
        @Headers("Content-Type: application/json")
        @POST("maps/api/directions/json")
        Call<ResponseRoute> getRoute(
                @Query("origin") String Origin,
                @Query("destination") String destination,
                @Query("sensor") boolean sensor,
                @Query("units") String units,
                @Query("mode") String mode
        );
    }
}
