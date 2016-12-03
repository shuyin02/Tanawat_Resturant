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
public class FoodTABLE {
    //Explicit
    private MySQLiteOpenHelper objMySQLitOpenHelper;
    private SQLiteDatabase writSqLiteDatabase, readSqLiteDatabase;

    public static final String FOOD_TABLE = "foodTABLE";
    public static final String COLUMN_ID_FOOD = "_id";
    public static final String COLUMN_FOOD ="Food";
    public static final String COLUMN_SOURCE ="Source";
    public static final String COLUMN_PRICE = "Price";

    public FoodTABLE(Context context){
        objMySQLitOpenHelper = new MySQLiteOpenHelper(context);
        writSqLiteDatabase = objMySQLitOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLitOpenHelper.getReadableDatabase();
    }//context
    public long addNewFood(String strFood, String strSource, String strPrice){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_FOOD,strFood);
        objContentValues.put(COLUMN_SOURCE,strSource);
        objContentValues.put(COLUMN_PRICE,strPrice);
        return writSqLiteDatabase.insert(FOOD_TABLE,null,objContentValues);
    }//addNewFood
}//Min class

