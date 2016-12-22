package com.psb.ipack.new_order.select_map;

import android.Manifest;
import android.animation.Animator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.psb.ipack.R;
import com.psb.ipack.logic.G;
import com.psb.ipack.new_order.Activity_new_order;
import com.psb.ipack.new_order.frg_feedBack;
import com.psb.ipack.new_order.serializ.GeoModel;
import com.psb.ipack.new_order.serializ.OrderSerialize;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Frg_select_map extends Fragment implements iV_SelectMap, OnMapReadyCallback {
    private static final String ORDER_VALUES = "defaultValues";
    
    private static frg_feedBack frg_feedBack;
    private View pView;
    private GoogleMap mMap;
    private P_SelectMap pSelectMap;

    private ImageView imageMarker;
    private TextView markerText;
    private CircularProgressButton btnRetry;
    private CardView cardViewOrderDetails;
    private TextView textPrice;
    private TextView textDistance;

    private OrderSerialize orderSerialize;

    public Frg_select_map() {
    }

    public static Frg_select_map newInstance(OrderSerialize orderSerialize) {
        Frg_select_map fragment = new Frg_select_map();
        Bundle args = new Bundle();
        args.putParcelable(ORDER_VALUES,orderSerialize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderSerialize = getArguments().getParcelable(ORDER_VALUES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(pView==null)pView = inflater.inflate(R.layout.fragment_frg_select_map, container, false);
        ///////////////// start set views //////////////////////////
        markerText = (TextView) pView.findViewById(R.id.frg_select_map_mark_location);
        btnRetry = (CircularProgressButton) pView.findViewById(R.id.frg_select_map_btn_retry);
        cardViewOrderDetails = (CardView) pView.findViewById(R.id.frg_select_map_card_order_details);
        textPrice = (TextView) pView.findViewById(R.id.frg_select_map_text_price);
        textDistance = (TextView) pView.findViewById(R.id.frg_select_map_text_distance);
        ///////////////// end set views ////////////////////////////
        pSelectMap = new P_SelectMap(getContext(), this, frg_feedBack.hasEndPoint());

        btnRetry.setIndeterminateProgressMode(true);

        if (frg_feedBack.hasEndPoint()) {
            markerText.setText("مبدا را انتخاب نماببد");
        } else {
            markerText.setText("محل خدمت را انتخاب نمایید");
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg_select_map);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pSelectMap.clickRetryBtn(btnRetry.getProgress());
            }
        });
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);

        } else {
            Log.d(G.LOG_TAG, "map is null");
        }
        return pView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity_new_order) {
            frg_feedBack = (frg_feedBack) context;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        frg_feedBack = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mMap.clear();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        if (frg_feedBack.hasEndPoint()) {
            markerText.setText("مبدا را انتخاب نماببد");
        } else {
            markerText.setText("محل خدمت را انتخاب نمایید");
        }
        imageMarker = (ImageView) pView.findViewById(R.id.frg_select_map_btn_pin);
        imageMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pSelectMap.setPoint(mMap.getCameraPosition().target);
            }
        });
/////////////////// start draw default values //////////////////////////////////////////////////////        
        if(orderSerialize.getStartPoint()!=null & orderSerialize.getEndPoint() !=null){
              try {
                    JSONArray arrRoute=new JSONArray(orderSerialize.getRouteRawString());
                    if(arrRoute.length()>0){
                        ArrayList<LatLng> route=new ArrayList<>();
                        for(int j=0;j<arrRoute.length();j++){
                            Gson gson=new Gson();
                            GeoModel geoModel = gson.fromJson(arrRoute.getString(j), GeoModel.class);
                            LatLng point=new LatLng(geoModel.latitude,geoModel.longitude);
                            route.add(point);
                        }
                        orderSerialize.setArrRoutes(route);
                        freezeMap();
                        onDrawEndLocation(orderSerialize.getStartPoint(),orderSerialize.getEndPoint(),route,orderSerialize.getDistance(),0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }else if (orderSerialize.getStartPoint()!=null){
            Log.d(G.LOG_TAG,"start draw start point");
            drawDefaultStartLocation(orderSerialize.getStartPoint());
        }
/////////////////// end draw default values ////////////////////////////////////////////////////////            
    }

    @Override
    public void onStartDrawRoute(LatLng endPoint) {
        mMap.addMarker(new MarkerOptions().position(endPoint));
        //TODO show progress
        freezeMap();
        btnRetry.setProgress(0);
        btnRetry.setProgress(50);
    }

    @Override
    public void onFailedDrawRoute() {
        //TODO disapear progress bar
        btnRetry.setProgress(-1);
    }

    @Override
    public void onRetryDrawRoute() {
        btnRetry.setProgress(0);
        btnRetry.setProgress(50);
    }

    @Override
    public void onDrawEndLocation(LatLng startPoint, LatLng endPoint, List<LatLng> arrRoute, long distance, long duration) {
        frg_feedBack.onLocationSelected();
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(startPoint));
        mMap.addMarker(new MarkerOptions().position(endPoint));
        
        PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.RED);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(startPoint);
        builder.include(endPoint);

        for (LatLng point : arrRoute) {
            rectLine.add(point);
            builder.include(point);
        }

        mMap.addPolyline(rectLine);

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 20);
        mMap.animateCamera(cu);

        btnRetry.setProgress(0);
        btnRetry.setProgress(100);
        
        
        int price=(int)distance*frg_feedBack.defaultPrice();
        
        if(frg_feedBack.hasEndPoint()){
            textPrice.setText(""+price+" ریال");
            textDistance.setText(""+distance+" کیلومتر");
            showOrderDetails(true);
        }
        

    }

    @Override
    public void onDrawStartLocation(LatLng startLatLng, boolean finished) {
        mMap.addMarker(new MarkerOptions().position(startLatLng).title("مبدا"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(startLatLng));
        if (finished) {
            frg_feedBack.onLocationSelected();
            freezeMap();
            btnRetry.setProgress(100);
        } else {
            markerText.setText("مقصد را انتخاب نمایید");
            btnRetry.setProgress(100);
            btnRetry.setVisibility(View.VISIBLE);
        }
    }
    
    private void drawDefaultStartLocation(LatLng startLatLng){
        mMap.addMarker(new MarkerOptions().position(startLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,15.0f));
        frg_feedBack.onLocationSelected();
        freezeMap();
        btnRetry.setProgress(100);
    }

    @Override
    public void onRenewLocation() {
   
        mMap.clear();
        mMap.getUiSettings().setAllGesturesEnabled(true);
        imageMarker.setVisibility(View.VISIBLE);
        markerText.setVisibility(View.VISIBLE);

        if (frg_feedBack.hasEndPoint()) {
            markerText.setText("مبدا را انتخاب نماببد");
        } else {
            markerText.setText("محل خدمت را انتخاب نمایید");
        }

        btnRetry.setVisibility(View.GONE);
        frg_feedBack.onRenewLocation();

        if (View.VISIBLE == cardViewOrderDetails.getVisibility()) {
            showOrderDetails(false);
        }
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return orderSerialize;
    }

    private void freezeMap() {
        mMap.getUiSettings().setAllGesturesEnabled(false);
        imageMarker.setVisibility(View.GONE);
        markerText.setVisibility(View.GONE);
        btnRetry.setVisibility(View.VISIBLE);
    }

    private void showOrderDetails(boolean isShow) {
        if (isShow) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                int cx = cardViewOrderDetails.getWidth() / 2;
                int cy = cardViewOrderDetails.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(cardViewOrderDetails, cx, cy, 0, finalRadius);
                cardViewOrderDetails.setVisibility(View.VISIBLE);
                anim.start();
            } else {
                cardViewOrderDetails.setVisibility(View.VISIBLE);
            }
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                int cx = cardViewOrderDetails.getWidth() / 2;
                int cy = cardViewOrderDetails.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(cardViewOrderDetails, cx, cy, finalRadius, 0);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        cardViewOrderDetails.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                anim.start();
            } else {
                cardViewOrderDetails.setVisibility(View.GONE);
            }
        }

    }

}
