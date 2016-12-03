package com.example.tanawat.tanawat_resturant;

/**
 * Created by Supanat on 12/2/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pc on 19/10/2559.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    //ExpTicit
    public static final String DATABASE_NAME = "Restaurant.db";
    private static final int DATABASE_VERSION =1;
    private static final String CREATE_USER_TABLE ="create table userTABLE "+
            "(_id integer primary key, User text, Password text, Name text)";
    private static final String CREATE_FOOD_TABLE ="create table foodTABLE"+
            "(_id integer primary key, Food text, Source text, Price text)";
    private static final String CREATE_ORDER_TABLE ="create table orderTABLE"+
            "(_in integer primary key, officer text, Desk text, Food text, item text)";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Constructor
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
