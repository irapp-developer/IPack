package com.psb.ipack.new_order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Sql;
import com.psb.ipack.new_order.serializ.GeoModel;
import com.psb.ipack.new_order.serializ.OrderSerialize;
import com.psb.ipack.splash.serializ.ResponseCatData;

/**
 * Created by mehdi on 12/4/16.
 */

public class Db_new_order {
    public static ResponseCatData getOrderDetail(Context context, int orderId) {
        ResponseCatData responseCatData = null;
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{
                "_id",
                "ParentId",
                "PersianTitle",
                "LatinTitle",
                "ImageUrl",
                "isActive",
                "isMovable",
                "sortOrder",
                "DefaultPrice",
                "modifiedOn"};
        Cursor c = db.query(Sql.TBL_CAT, columns, "_id=?", new String[]{"" + orderId}, null, null, null);
        if (c.moveToFirst()) {
            responseCatData = new ResponseCatData();
            responseCatData.setId(c.getInt(0));
            responseCatData.setParentId(c.getInt(1));
            responseCatData.setPersianTitle(c.getString(2));
            responseCatData.setLatinTitle(c.getString(3));
            responseCatData.setImageUrl(c.getString(4));
            responseCatData.setIsActive(c.getInt(5));
            responseCatData.setIsMovable(c.getInt(6));
            responseCatData.setSortOrder(c.getInt(7));
            responseCatData.setDefaultPrice(c.getInt(8));
            responseCatData.setModifiedOn(c.getString(9));
        }
        db.close();
        sql.close();
        return responseCatData;
    }

    public static long insertNewOrder(Context context, OrderSerialize orderSerialize) {
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("startLat", orderSerialize.getStartPoint().latitude);
        cv.put("startLng", orderSerialize.getStartPoint().longitude);
        cv.put("endLat", (orderSerialize.getEndPoint()!=null?orderSerialize.getEndPoint().latitude:0.0));
        cv.put("endLng", (orderSerialize.getEndPoint()!=null?orderSerialize.getEndPoint().longitude:0.0));
        cv.put("orderId", orderSerialize.getOrderId());
        cv.put("orderName", orderSerialize.getOrderName());
        cv.put("defaultPrice", orderSerialize.geDefaultPrice());
        cv.put("finalPrice", orderSerialize.getFinalPrice());
        cv.put("phoneNumber", orderSerialize.getPhoneNumber());
        cv.put("address", orderSerialize.getAddress());
        cv.put("userName", orderSerialize.getFirstName());
        cv.put("descr", orderSerialize.getDescr());
        cv.put("distance", orderSerialize.getDistance());
        cv.put("createTime", orderSerialize.getCreateTime());
        cv.put("ServerRequestId", orderSerialize.getServerRequestId());

        org.json.JSONArray arrRoute = new org.json.JSONArray();
        if(orderSerialize.getArrRoutes()!=null) {
            for (LatLng point : orderSerialize.getArrRoutes()) {
                Gson gson=new Gson();
                GeoModel geoModel=new GeoModel(point);
                arrRoute.put(gson.toJson(geoModel));
            }
        }
        cv.put("route", arrRoute.toString());
            
        long result=db.insert(Sql.TBL_ORDER_DETAILS,null,cv);
        

        db.close();
        sql.close();
        return result;

    }
}
