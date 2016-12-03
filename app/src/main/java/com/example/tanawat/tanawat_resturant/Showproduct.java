package com.example.tanawat.tanawat_resturant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class Showproduct extends AppCompatActivity {

    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        listView = (ListView) findViewById(R.id.listView);

        SynJSON synJSON = new SynJSON();
        synJSON.execute();
    }//onCreate

    public class SynJSON extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            try{
                String strURL = "http://www.csclub.ssru.ac.th/s56122201041/Food/php_get_foodTABLE.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response =okHttpClient.newCall(request).execute();
                return response.body().string();

            }catch (Exception e){
                Log.d("Restaurant","doIn"+e.toString());
                return null;

            }
        }//doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("Restaurant", "Response ==>"+s);
                JSONArray jsonArray = new JSONArray(s);

                String[] iconStrings = new String[jsonArray.length()];
                String[] titleStrings = new String[jsonArray.length()];
                String[] priceString = new  String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    iconStrings[i] = jsonObject.getString("Source");
                    titleStrings[i] = jsonObject.getString("Food");
                    priceString[i] = jsonObject.getString("Price");
                }//for

                ProductAdapter productAdapter = new ProductAdapter(Showproduct.this, iconStrings, titleStrings,priceString);
                listView.setAdapter(productAdapter);

            }catch (Exception e){
                Log.d("Restaurant","onPost ==>"+e.toString());
            }
        }//onPostExecute
    }//SynJSON
}//Min Class
