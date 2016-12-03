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
public class UserTABLE {
    private MySQLiteOpenHelper objMySQLitOpenHelper;
    private SQLiteDatabase writSqLiteDatabase, readSqLiteDatabase;

    public static final String USER_TABLE = "userTABLE";
    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_USER ="User";
    public static final String COLUMN_PASSWORD ="Password";
    public static final String COLUMN_NAME = "Name";

    public UserTABLE(Context context){
        objMySQLitOpenHelper = new MySQLiteOpenHelper(context);
        writSqLiteDatabase = objMySQLitOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLitOpenHelper.getReadableDatabase();
    }//constructor

    public long addNewUser(String strUser, String strPassword, String strName){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER,strUser);
        objContentValues.put(COLUMN_PASSWORD,strPassword);
        objContentValues.put(COLUMN_NAME,strName);
        return writSqLiteDatabase.insert(USER_TABLE,null,objContentValues);

    }//addNewUser
}
