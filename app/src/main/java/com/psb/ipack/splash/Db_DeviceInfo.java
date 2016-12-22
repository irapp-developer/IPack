package com.psb.ipack.splash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Sql;
import com.psb.ipack.splash.serializ.City;
import com.psb.ipack.splash.serializ.DataCity;
import com.psb.ipack.splash.serializ.ResponseCatData;

import java.util.List;

import static com.psb.ipack.App.getContext;

/**
 * Created by mehdi on 11/30/16.
 */

public class Db_DeviceInfo {

    public static int getCatCount(Context context) {
        int result = 0;
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{"_id"};
        Cursor c = db.query(Sql.TBL_CAT, columns, null, null, null, null, null);
        result = c.getCount();
        db.close();
        sql.close();
        return result;
    }
    public static int getCityCount(Context context) {
        int result = 0;
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{"_id"};
        Cursor c = db.query(Sql.TBL_CITY, columns, null, null, null, null, null);
        result = c.getCount();
        db.close();
        sql.close();
        return result;
    }

    public static String getLastCatModifyTime(Context context) {
        String result = "0000-00-00 00:00:00";
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{"modifiedOn"};
        Cursor c = db.query(Sql.TBL_CAT, columns, null, null, null, null, "modifiedOn DESC");
        if (c.moveToFirst()) {
            result = c.getString(0);
        }
        db.close();
        sql.close();
        Log.d(G.LOG_TAG, "last modified is :" + result);
        return result;
    }

    public static void updateCats(Context context, List<ResponseCatData> listResponse) {
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        for (ResponseCatData responseData : listResponse) {
            ContentValues cv = new ContentValues();
            cv.put("_id", responseData.getId());
            cv.put("ParentId", responseData.getParentId());
            cv.put("PersianTitle", responseData.getPersianTitle());
            cv.put("LatinTitle", responseData.getLatinTitle());
            cv.put("ImageUrl", responseData.getImageUrl());
            cv.put("isActive", responseData.getIsActive());
            cv.put("isMovable", responseData.getIsMovable());
            cv.put("sortOrder", responseData.getSortOrder());
            cv.put("modifiedOn", responseData.getModifiedOn());
            cv.put("DefaultPrice", responseData.getDefaultPrice());

            if (db.update(Sql.TBL_CAT, cv, "_id=?", new String[]{"" + responseData.getId()}) == 0) {
                Log.d(G.LOG_TAG, "->inserted");
                db.insert(Sql.TBL_CAT, null, cv);
            }
        }

        db.close();
        sql.close();
    }

    public static boolean hasCity(Context context) {
        int counterProvince = 0;
        int counterCity = 0;
        Sql sql = new Sql(context, G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{"_id"};
        Cursor c = db.query(Sql.TBL_PROVINCE, columns, null, null, null, null, null);
        counterProvince = c.getCount();
        c = db.query(Sql.TBL_CITY, columns, null, null, null, null, null);
        counterCity = c.getCount();
        db.close();
        sql.close();

        if (counterProvince == 0 || counterCity == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void saveCity(Context context,List<DataCity> arrCity) {
        Sql sql = new Sql(getContext(), G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        ContentValues cv = new ContentValues();
        for (DataCity dataCity : arrCity) {
            cv.clear();
            cv.put("_id", dataCity.getProvinceId());
            cv.put("ProvinceName", dataCity.getProvinceName());
            Log.d(G.LOG_TAG,"ProvinceName is :"+dataCity.getProvinceId());
            db.insert(Sql.TBL_PROVINCE, null, cv);
            for (City city : dataCity.getCities()) {
                cv.clear();
                cv.put("_id", city.getCityId());
                cv.put("ProvinceId", city.getProvinceId());
                cv.put("CityName", city.getCityName());

                db.insert(Sql.TBL_CITY, null, cv);
            }
        }

        db.close();
        sql.close();
    }
}
