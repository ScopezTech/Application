package com.example.my_app;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static boolean checkLoginCredentials(String result, String userName, String password){
        String md5UserName = getMd5(userName);
        String md5Password = getMd5(password);
        boolean status = false;
            try{

                JSONArray jsonArray = new JSONArray(result);
                System.out.println(jsonArray);
                for (int i = 0; i <jsonArray.length(); i++) {
                    System.out.println(md5Password+"   "+md5UserName);
                    System.out.println(i);
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println(jsonObject);
                    //JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                    if( jsonObject.getString("username").equals(md5UserName) &&  jsonObject.getString("password").equals(md5Password)  ) {
                        System.out.println(" It is true but not shown "+md5Password+md5UserName);
                        status = true;
                        break;
                    }
                }
            }catch (JSONException je){
                je.printStackTrace();
            }
        System.out.println(status);
        return status;
    }
    public static String getJSONFromAssets(Context context) {
        String re = null;
        try {

            InputStream inputData = context.getAssets().open("user.txt");
            int size = inputData.available();
            byte[] buffer = new byte[size];
            inputData.read(buffer);
            inputData.close();
            re = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return re;
    }
    public  static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
