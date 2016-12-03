package com.example.tanawat.tanawat_resturant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private OrderTABLE objOrderTABLE;
    private MySQLite mySQLite;

    private EditText userEDITText,passwordEditText;
    private String userString, paswordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connected SQLite
        connectedSQLite();

        //Test add value
        //testAddValue();

        //Synchronize MySQL to SQLite
        synAndDelete();

        //Request SQLite
        mySQLite = new MySQLite(this);

        userEDITText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

    }//onCreate
    public void clickSingUPMin(View view){
        startActivity(new Intent(this,Sing_up.class));
    }
    public void clickSignInMain(View view){
        userString = userEDITText.getText().toString().trim();
        paswordString = passwordEditText.getText().toString().trim();

        //check space
        if (userString.equals("") || paswordString.equals("")){
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาใส่ใส่ข้อมูลทุกช่อง");
        }else {
            checkUser();
        }
    }//clickSidnIn
    private void synAndDelete(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME,MODE_PRIVATE,null);
        sqLiteDatabase.delete(MySQLite.user_table,null,null);
        MySynJSON mySynJSON = new  MySynJSON();
        mySynJSON.execute();
    }//SynAndDelete

    public class MySynJSON extends AsyncTask <Void, Void, String>{


        @Override
        protected String doInBackground(Void... voids) {
            try {
                String strURL = "http://www.csclub.ssru.ac.th/s56122201041/Food/php_get_userTABLE.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                Log.d("Satien","doInBack ==>"+ e.toString());
                return null;
            }
        }//doInBackground

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Log.d("Satien","strJSON ==>"+s);
            try{
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strUser = jsonObject.getString(MySQLite.column_user);
                    String strPassword = jsonObject.getString(mySQLite.column_password);
                    String strName = jsonObject.getString(mySQLite.column_name);
                    mySQLite.addNewUser(strUser, strPassword, strName);
                }//for
                Toast.makeText(MainActivity.this,"Synchromizr mySQL to SOLite Finsh",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("Satien","onPost ==>" + e.toString());
            }
        }//onPostExcute
    }

    private void testAddValue(){
        objUserTABLE.addNewUser("testUser","testPass","testName");
        objFoodTABLE.addNewFood("testFood","testSource","testPrice");
        objOrderTABLE.addOrder("testOfficer","testDesk","testFood","testItem");
    }//testAddValue

    private void connectedSQLite(){
        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);
        objOrderTABLE = new OrderTABLE(this);
    }//connectedSQLite
    private void checkUser() {
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User =" + "'" + userString + "'", null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                resultStrings[i] = cursor.getString(i);
            }
            cursor.close();
            //CheckPassword
            if (paswordString.equals(resultStrings[2])) {
                Toast.makeText(this, "ยินดีต้องรับ" + resultStrings[3], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Showproduct.class);
                intent.putExtra("Result", resultStrings);
                startActivity(intent);
                finish();
            } else {
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this,"passผิด","ผิมใหม่");
            }
        }catch (Exception e){
            MyAlert alert = new MyAlert();
            alert.myDialog(this,"ไม่มีuser","ไม่มี"+userString+"ในฐานข้อมูล");
            Log.i("DB error",e.toString());

        }
    }//checkUser
}
