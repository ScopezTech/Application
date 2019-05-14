package com.example.my_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.androidhire.splashscreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Choice_get extends AppCompatActivity {
    ListView listView;
    String loc_file;
    ArrayList<Info> infoArrayList_show = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_get);
        new AsyncTaskGet().execute();
    }
    private void onDisplay(){

        //Info info2 = new Info("18 yash weed street 696969","12/4/2","2:20 pm","$690","Yashguy65");
        //infoArrayList.add(info2);
        infoListAdapter adapter = new infoListAdapter(this,R.layout.single_list_item,infoArrayList_show);
        listView =  findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
    private class AsyncTaskGet extends AsyncTask<String , String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                    for(int i = 0; i < JsonArr.length(); i++){
                        JSONObject JSONObject = JsonArr.getJSONObject(i);
                        JSONObject jsonProp = JSONObject.getJSONObject("properties");
                        String loc_check =jsonProp.getString("Location");
                        loc_file="NC State University Public Safety Building  2610 Wolf Village Way, Raleigh, NC, 27695";
                        if(loc_check.equals(loc_file)){
                            String location ="Location:"+ jsonProp.getString("Location");
                            String date = "Date: \n "+jsonProp.getString("Date");
                            String time = "Time \n"+jsonProp.getString("Time");
                            String fees = "Fee \n"+jsonProp.getString("Fee");
                            String inst = "Instructor \n"+jsonProp.getString("Instructor");
                            Info infoJson1 = new Info(location,date,time,fees,inst);
                            infoArrayList_show.add(infoJson1);


                        }
                    }
                }onDisplay();}
            catch (JSONException e) {
                e.printStackTrace();
            }
        }}}
