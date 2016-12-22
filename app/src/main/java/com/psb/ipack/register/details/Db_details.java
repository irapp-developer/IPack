package com.psb.ipack.register.details;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Sql;
import com.psb.ipack.splash.serializ.City;
import com.psb.ipack.splash.serializ.DataCity;
import com.psb.ipack.splash.serializ.ResponseCatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/14/16.
 */

public class Db_details {
    private Context context;

    public Db_details(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public  List<ResponseCatData> getCatList() {
        ArrayList<ResponseCatData> result = new ArrayList<>();
        Sql sql = new Sql(getContext(), G.DB_VERSION);
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
                "modifiedOn"};
        Cursor c = db.query(Sql.TBL_CAT, columns, "ParentId IS NULL", null, null, null, null);

        if(c.moveToFirst()){
            for(int i=0;i<c.getCount();i++){
                Log.d(G.LOG_TAG,"parent id is :"+c.getString(1));
                ResponseCatData responseCatData=new ResponseCatData();
                responseCatData.setId(c.getInt(0));
                responseCatData.setParentId(c.getInt(1));
                responseCatData.setPersianTitle(c.getString(2));
                responseCatData.setLatinTitle(c.getString(3));
                responseCatData.setImageUrl(c.getString(4));
                responseCatData.setIsActive(c.getInt(5));
                responseCatData.setIsMovable(c.getInt(6));
                responseCatData.setSortOrder(c.getInt(7));
                responseCatData.setModifiedOn(c.getString(8));

                result.add(responseCatData);
                c.moveToNext();
            }
        }
        db.close();
        sql.close();
        return result;
    }

    public  List<ResponseCatData> getSubCatList(int parentId) {
        ArrayList<ResponseCatData> result = new ArrayList<>();
        Sql sql = new Sql(getContext(), G.DB_VERSION);
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
                "modifiedOn"};
        Cursor c = db.query(Sql.TBL_CAT, columns, "ParentId=?", new String[]{""+parentId}, null, null, null);
        if(c.moveToFirst()){
            for(int i=0;i<c.getCount();i++){
                ResponseCatData responseCatData=new ResponseCatData();
                responseCatData.setId(c.getInt(0));
                responseCatData.setParentId(c.getInt(1));
                responseCatData.setPersianTitle(c.getString(2));
                responseCatData.setLatinTitle(c.getString(3));
                responseCatData.setImageUrl(c.getString(4));
                responseCatData.setIsActive(c.getInt(5));
                responseCatData.setIsMovable(c.getInt(6));
                responseCatData.setSortOrder(c.getInt(7));
                responseCatData.setModifiedOn(c.getString(8));

                result.add(responseCatData);
                c.moveToNext();
            }
        }
        db.close();
        sql.close();
        return result;
    }
    
    public List<DataCity> getProvinceList(){
        ArrayList<DataCity> result = new ArrayList<>();
        Sql sql = new Sql(getContext(), G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{
                "_id",
                "ProvinceName"};
        Cursor c = db.query(Sql.TBL_PROVINCE, columns, null, null, null, null, null);

        if(c.moveToFirst()){
            Log.d(G.LOG_TAG,"counter city is :"+c.getCount());
            for(int i=0;i<c.getCount();i++){
                DataCity dataCity=new DataCity();
                dataCity.setProvinceId(c.getInt(0));
                dataCity.setProvinceName(c.getString(1));
                result.add(dataCity);
                c.moveToNext();
            }
        }
        db.close();
        sql.close();
        
        return result;
    }
 
    public List<City> getCityList(int piovenceId){
        ArrayList<City> result = new ArrayList<>();
        Sql sql = new Sql(getContext(), G.DB_VERSION);
        SQLiteDatabase db = sql.getReadableDatabase();
        String[] columns = new String[]{
                "_id",
                "ProvinceId",
                "CityName"};
        Cursor c = db.query(Sql.TBL_CITY, columns, "ProvinceId=?", new String[]{""+piovenceId}, null, null, null);

        if(c.moveToFirst()){
            for(int i=0;i<c.getCount();i++){
                City city=new City();
                city.setCityId(c.getInt(0));
                city.setProvinceId(c.getInt(1));
                city.setCityName(c.getString(2));
               
                result.add(city);
                c.moveToNext();
            }
        }
        db.close();
        sql.close();
        return result;
    }
}
