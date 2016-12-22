package com.psb.ipack.new_order.factor;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.psb.ipack.R;
import com.psb.ipack.logic.G;
import com.psb.ipack.new_order.Activity_new_order;
import com.psb.ipack.new_order.serializ.OrderSerialize;
import com.psb.ipack.new_order.frg_feedBack;

public class Frg_Factor extends Fragment implements OnMapReadyCallback,iV_Factor {

    private static frg_feedBack frg_feedBack;
    private static final String ORDER_VALUES = "defaultValues";
    private OrderSerialize orderSerialize;

    private View pView;
    private GoogleMap mMap;
    private AppCompatTextView textName;
    private AppCompatTextView textPhoneNumber;
    private AppCompatTextView textAddress;
    private AppCompatTextView textDescr;
    private AppCompatTextView textPrice;
    private AppCompatTextView textMoreInfo;


    public Frg_Factor() {
        // Required empty public constructor
    }


    public static Frg_Factor newInstance(OrderSerialize orderSerialize) {
        Frg_Factor fragment = new Frg_Factor();
        Bundle args = new Bundle();
        args.putParcelable(ORDER_VALUES, orderSerialize);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(pView==null)pView = inflater.inflate(R.layout.fragment_factor, container, false);

        textName = (AppCompatTextView) pView.findViewById(R.id.frg_factor_name);
        textAddress = (AppCompatTextView) pView.findViewById(R.id.frg_factor_address);
        textPhoneNumber = (AppCompatTextView) pView.findViewById(R.id.frg_factor_phoneNumber);
        textDescr = (AppCompatTextView) pView.findViewById(R.id.frg_factor_desc);
        textPrice = (AppCompatTextView) pView.findViewById(R.id.frg_factor_price);
        textMoreInfo = (AppCompatTextView) pView.findViewById(R.id.frg_factor_details);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg_factor_map);
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
        mMap.getUiSettings().setAllGesturesEnabled(false);
//        updateViews();
    }
    
    public void updateViews() {
            if(mMap!=null & orderSerialize.getStartPoint()!=null){
                mMap.clear();
                textName.setText("نام درخواست دهنده :"+orderSerialize.getFirstName()); 
                textAddress.setText("آدرس :"+orderSerialize.getAddress());
                textPhoneNumber.setText("شماره تماس"+orderSerialize.getPhoneNumber()) ;
                textDescr.setText("توضیحات :"+orderSerialize.getDescr()) ;
                mMap.addMarker(new MarkerOptions().position(orderSerialize.getStartPoint()));
                if(orderSerialize.getFinalPrice()==0){
                    textPrice.setText("هزینه خدمت : توافقی");
                }else{
                    textPrice.setText("هزینه خدمت : "+orderSerialize.getFinalPrice()+" ریال");
                }
                
                if(orderSerialize.getEndPoint()!=null & orderSerialize.getArrRoutes()!=null){
                    mMap.addMarker(new MarkerOptions().position(orderSerialize.getEndPoint()));
                    textMoreInfo.setText("مسافت مسیر :"+orderSerialize.getDistance()+" کیلومتر");
                    textMoreInfo.setVisibility(View.VISIBLE);
                    
                    PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.RED);
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();

                    builder.include(orderSerialize.getStartPoint());
                    builder.include(orderSerialize.getEndPoint());

                    for (LatLng point : orderSerialize.getArrRoutes()) {
                        rectLine.add(point);
                        builder.include(point);
                    }

                    mMap.addPolyline(rectLine);

                    LatLngBounds bounds = builder.build();
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 20);
                    mMap.moveCamera(cu);
                    
                }else{
                    CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(orderSerialize.getStartPoint(),15.0f);
                    mMap.moveCamera(cu);
                    textMoreInfo.setVisibility(View.GONE);
                }
                
            }
    }
    @Override
    public OrderSerialize getOrderSerialize() {
        return this.orderSerialize;
    }
}
