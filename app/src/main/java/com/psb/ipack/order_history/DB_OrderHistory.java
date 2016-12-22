package com.psb.ipack.order_history;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Sql;
import com.psb.ipack.new_order.serializ.GeoModel;
import com.psb.ipack.new_order.serializ.OrderSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/11/16.
 */

public class DB_OrderHistory {
    Context context;

    public DB_OrderHistory(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public  List<OrderSerialize> getOrderHistory() {
        ArrayList<OrderSerialize> responseOrderSerialize = new ArrayList<>();
        Sql sql = new Sql(getContext(), G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{
                "_id ",
                "startLat",
                "startLng",
                "endLat",
                "endLng",

                "orderId",
                "orderName",
                "defaultPrice",
                "finalPrice",
                "phoneNumber",
                "address",
                "userName",
                "descr",
                "route",
                "distance",
                "ServerRequestId",
                "createTime"};

        Cursor c = db.query(Sql.TBL_ORDER_DETAILS, columns, null, null, null, null, "datetime(createTime) DESC");
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {

                OrderSerialize orderSerialize = new OrderSerialize();
                
                LatLng startPoint = new LatLng(c.getDouble(1), c.getDouble(2));
                LatLng endPoint=null;

                GeoModel geoModelStart=new GeoModel(c.getDouble(1), c.getDouble(2));
                GeoModel geoModelEnd=null;
                if(c.getDouble(3)!=0 && c.getDouble(4)!=0){
                    endPoint= new LatLng(c.getDouble(3), c.getDouble(4));
                    geoModelEnd=new GeoModel(c.getDouble(3), c.getDouble(4));
                }
                orderSerialize.setStartPoint(startPoint);
                orderSerialize.setEndPoint(endPoint);

                orderSerialize.setSourceGeo(geoModelStart);
                orderSerialize.setDestenationGeo(geoModelEnd);

                orderSerialize.setOrderId(c.getInt(5));
                orderSerialize.setOrderName(c.getString(6));
                orderSerialize.setDefaultPrice(c.getInt(7));
                orderSerialize.setPrice(c.getString(7));
                orderSerialize.setFinalPrice(c.getInt(8));
                orderSerialize.setPhoneNumber(c.getString(9));
                orderSerialize.setAddress(c.getString(10));
                orderSerialize.setFirstName(c.getString(11));
                orderSerialize.setDescr(c.getString(12));
                orderSerialize.setRouteRawString(c.getString(13));
                orderSerialize.setDistance(c.getInt(14));
                orderSerialize.setServerRequestId(c.getInt(15));
                orderSerialize.setCreateTime(c.getString(16));

                responseOrderSerialize.add(orderSerialize);
                
                /*try {
                    JSONArray arrRoute=new JSONArray(c.getString(13));
                    if(arrRoute.length()>0){
                        ArrayList<LatLng> route=new ArrayList<>();
                        for(int j=0;j<arrRoute.length();j++){
                            Gson gson=new Gson();
                            GeoModel geoModel = gson.fromJson(arrRoute.getString(j), GeoModel.class);
                            LatLng point=new LatLng(geoModel.latitude,geoModel.longitude);
                            
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/


                c.moveToNext();
            }
        }

        db.close();
        sql.close();
        return responseOrderSerialize;
    }

    public  OrderSerialize getOrder(long orderId) {
        OrderSerialize orderSerialize = null;
        Sql sql = new Sql(getContext(), G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{
                "_id ",
                "startLat",
                "startLng",
                "endLat",
                "endLng",

                "orderId",
                "orderName",
                "defaultPrice",
                "finalPrice",
                "phoneNumber",
                "address",
                "userName",
                "descr",
                "route",
                "distance",
                "ServerRequestId",
                "createTime"};

        Cursor c = db.query(Sql.TBL_ORDER_DETAILS, columns, "_id=?",new String[]{""+orderId}, null, null, "datetime(createTime) DESC");
        if (c.moveToFirst()) {


            orderSerialize = new OrderSerialize();

            LatLng startPoint = new LatLng(c.getDouble(1), c.getDouble(2));
            LatLng endPoint=null;

            GeoModel geoModelStart=new GeoModel(c.getDouble(1), c.getDouble(2));
            GeoModel geoModelEnd=null;
            if(c.getDouble(3)!=0 && c.getDouble(4)!=0){
                endPoint= new LatLng(c.getDouble(3), c.getDouble(4));
                geoModelEnd=new GeoModel(c.getDouble(3), c.getDouble(4));
            }
            orderSerialize.setStartPoint(startPoint);
            orderSerialize.setEndPoint(endPoint);

            orderSerialize.setSourceGeo(geoModelStart);
            orderSerialize.setDestenationGeo(geoModelEnd);

            orderSerialize.setOrderId(c.getInt(5));
            orderSerialize.setOrderName(c.getString(6));
            orderSerialize.setDefaultPrice(c.getInt(7));
            orderSerialize.setPrice(c.getString(7));
            orderSerialize.setFinalPrice(c.getInt(8));
            orderSerialize.setPhoneNumber(c.getString(9));
            orderSerialize.setAddress(c.getString(10));
            orderSerialize.setFirstName(c.getString(11));
            orderSerialize.setDescr(c.getString(12));
            orderSerialize.setRouteRawString(c.getString(13));
            orderSerialize.setDistance(c.getInt(14));
            orderSerialize.setServerRequestId(c.getInt(15));
            orderSerialize.setCreateTime(c.getString(16));

        }

        db.close();
        sql.close();
        return orderSerialize;
    }

}
