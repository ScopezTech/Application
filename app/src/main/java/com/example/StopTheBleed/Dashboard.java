package com.example.my_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;
import android.widget.TextView;
import com.androidhire.splashscreen.R;
import com.androidhire.splashscreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    ListView listView;
    ArrayList<Info> infoArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView textView= findViewById(R.id.textView);
        new AsyncTaskGetMarker().execute();

    }


    private void onDisplay(){

        //Info info2 = new Info("18 yash weed street 696969","12/4/2","2:20 pm","$90","Yashguy65");
        //infoArrayList.add(info2);
        infoListAdapter adapter = new infoListAdapter(this,R.layout.single_list_item,infoArrayList);
        listView =  findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }







    public String getJSONFromAssets() {
        String json = null;
        try {
            InputStream inputData = getAssets().open("classes.txt");
            int size = inputData.available();
            byte[] buffer = new byte[size];
            inputData.read(buffer);
            inputData.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class AsyncTaskGetMarker extends AsyncTask<String , String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String classesJsonString = getJSONFromAssets();
/*                JSONArray classesJsonArray = new JSONArray(classesJsonString);
                return classesJsonArray;
            } catch (JSONException e) {
                e.printStackTrace();
            }  */
            //This will only happen if an exception is thrown above:
            return classesJsonString;
        }

        protected void onPostExecute (String result){
            try {
                if (result != null) {
                    JSONObject Json = new JSONObject(result);
                    JSONArray JsonArr = Json.getJSONArray("features");
                    for (int i = 0; i < JsonArr.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = JsonArr.getJSONObject(i);
                            JSONObject jsongeometry = jsonObject.getJSONObject("geometry");
                            JSONArray jsonLatlng = jsongeometry.getJSONArray("coordinates");
                            JSONObject jsonProp = jsonObject.getJSONObject("properties");
                            String location ="Location:"+ jsonProp.getString("Location");
                            String latitude = jsonLatlng.getString(1);
                            String longitude = jsonLatlng.getString(0);
                            String date = "Date: \n "+jsonProp.getString("Date");
                            String time = "Time \n"+jsonProp.getString("Time");
                            String fees = "Fee \n"+jsonProp.getString("Fee");
                            String inst = "Instructor \n"+jsonProp.getString("Instructor");
                            Info infoJson = new Info(location,date,time,fees,inst);
                            infoArrayList.add(infoJson);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                onDisplay();}
            catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

}
