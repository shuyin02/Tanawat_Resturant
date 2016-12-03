package com.example.tanawat.tanawat_resturant;

/**
 * Created by Supanat on 12/2/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Pc on 26/10/2559.
 */
public class MySQLite {
    //Explicit
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTable";
    public static final String column_id ="_id";
    public static final String column_user ="User";
    public static final String column_password ="Password";
    public static final String column_name = "Name";

    public MySQLite(Context context){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
    }//Constructor

    public long addNewUser(String strUser, String strPassword,String strName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_user,strUser);
        contentValues.put(column_password,strPassword);
        contentValues.put(column_name,strName);
        return sqLiteDatabase.insert(user_table, null,contentValues);
    }
}
