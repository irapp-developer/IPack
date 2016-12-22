package com.psb.ipack.dialog_new_order;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.psb.ipack.logic.G;
import com.psb.ipack.logic.Sql;
import com.psb.ipack.splash.serializ.ResponseCatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/1/16.
 */

public class Db_dialog_new_order {
    
    public static List<ResponseCatData> getCatList(Context context) {
        ArrayList<ResponseCatData> result = new ArrayList<>();
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

    public static List<ResponseCatData> getSubCatList(Context context,int parentId) {
        ArrayList<ResponseCatData> result = new ArrayList<>();
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
}
