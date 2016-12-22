package com.psb.ipack.logic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mehdi on 7/17/15 AD.
 */
public class Sql extends SQLiteOpenHelper {
    //------------------------------------------
    static final String dbName = "db_ipack_customers_1";

    public static final String TBL_SETTINGS = "tbl_settings";
    public static final String TBL_CAT = "tbl_cat";
    public static final String TBL_ORDER_DETAILS = "tbl_order_details";
    public static final String TBL_PROVINCE = "tbl_province";
    public static final String TBL_CITY = "tbl_city";


    //------------------------------------------
    public Sql(Context context, int version) {
        super(context, dbName, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_SETTINGS + " (name TEXT,dim TEXT)");
        db.execSQL("CREATE TABLE " + TBL_CAT + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ParentId INTEGER," +
                "PersianTitle TEXT," +
                "LatinTitle TEXT," +
                "ImageUrl TEXT," +
                "isActive BOOLEAN," +
                "isMovable BOOLEAN," +
                "sortOrder BOOLEAN," +
                "DefaultPrice INTEGER," +
                "modifiedOn CURRENT_TIMESTAMP)" +
                "");
        db.execSQL("CREATE TABLE " + TBL_ORDER_DETAILS + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "startLat TEXT," +
                "startLng TEXT," +
                "endLat TEXT," +
                "endLng TEXT," +
                "orderId INTEGER," +
                "orderName TEXT," +
                "defaultPrice TEXT," +
                "finalPrice TEXT," +
                "phoneNumber TEXT," +
                "address TEXT," +
                "userName TEXT," +
                "descr TEXT," +
                "route TEXT," +
                "distance TEXT," +
                "ServerRequestId INTEGER," +
                "createTime CURRENT_TIMESTAMP)" 
        );
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_PROVINCE + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ProvinceName TEXT)" +
                "");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_CITY + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ProvinceId INTEGER," +
                "CityName TEXT)"
        );
//

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_CAT);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_ORDER_DETAILS);
        onCreate(db);
    }


}
