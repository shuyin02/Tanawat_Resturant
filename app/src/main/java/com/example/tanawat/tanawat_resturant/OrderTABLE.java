package com.example.tanawat.tanawat_resturant;

/**
 * Created by Supanat on 12/2/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Pc on 19/10/2559.
 */
public class OrderTABLE {
    private MySQLiteOpenHelper objMySQLitOpenHelper;
    private SQLiteDatabase writSqLiteDatabase, readSqLiteDatabase;

    public static final String ORDER_TABLE = "orderTABLE";
    public static final String COLUMN_ID_ORDER = "_id";
    public static final String COLUMN_OFFICER ="Officer";
    public static final String COLUMN_DESK ="Desk";
    public static final String COLUME_FOOD="Food";
    public static final String COLUMN_ITEM = "Item";

    public OrderTABLE(Context context){
        objMySQLitOpenHelper = new MySQLiteOpenHelper(context);
        writSqLiteDatabase = objMySQLitOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLitOpenHelper.getReadableDatabase();
    }//constructor
    public long addOrder(String strOfficer, String strDesk , String strFood , String strItem){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER,strOfficer);
        objContentValues.put(COLUMN_DESK,strDesk);
        objContentValues.put(COLUME_FOOD,strFood);
        objContentValues.put(COLUMN_ITEM,strItem);
        return writSqLiteDatabase.insert(ORDER_TABLE,null,objContentValues);
    }//addOrder
}
