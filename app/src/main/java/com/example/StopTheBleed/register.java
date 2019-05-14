package com.example.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import  org.json.JSONArray;
import org.json.JSONException;
import  org.json.JSONObject;
import com.androidhire.splashscreen.R;
import org.json.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class register extends AppCompatActivity implements View.OnClickListener {
    Button btnregister;
    Boolean status = FALSE;
    Boolean status2 = FALSE;
    Boolean status3 = FALSE;
    Boolean status4 = FALSE;
    Boolean status5 = FALSE;
    Boolean status6 = FALSE;
    EditText etUsername ,etPassword,etPassword_re;
    String user , pass;
    TextView hello;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword_re= (EditText) findViewById(R.id.etPassword_re) ;
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnregister:
                Boolean status = FALSE;
                Boolean status2 = FALSE;
                Boolean status3 = FALSE;
                Boolean status4 = FALSE;
                Boolean status5 = FALSE;
                Boolean status6 = FALSE;
                String pass_re = etPassword_re.getText().toString();
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                System.out.println(pass.length());
                String display ="You need the password to have more than six characters";
                toCheck(pass);
                toCheck(user);
                toCheck(pass_re);
                isSame(pass,pass_re);
                isThere(pass);
                System.out.println(status);
                System.out.println(status2);
                System.out.println(status3);
                if (status.equals(TRUE) &&status2.equals(TRUE) && status3.equals(TRUE)) {
                    pass.trim();
                    pass_re.trim();
                    user.trim();
                   String pass_f=Utils.getMd5(pass);
                    String pass_ref=Utils.getMd5(pass_re);
                    String users=Utils.getMd5(user);
                    startActivity(new Intent(this, MainActivity.class));
                }

                break;}

        }

    public void toCheck(String checker){
        String display ="You need the password/Username to have more than six characters";
        if (checker.length()<8){
            System.out.println("lola");
            Toast.makeText(getApplicationContext(),display,Toast.LENGTH_LONG).show();
            etPassword.getText().clear();
            etUsername.getText().clear(); }
        else{
            status=TRUE; }}

            public void isSame(String pass1 ,String pass2){
            String display_same ="Your password are different";
                if (pass1.equals(pass2)){
                    status2=TRUE;
        }   else{
                    Toast.makeText(getApplicationContext(),display_same,Toast.LENGTH_LONG).show();
                    etPassword.getText().clear();
                    etUsername.getText().clear(); } }
            public  void isThere(String ch){
            String display_match=" Please ensure that your password contains a Special Character";
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
            Matcher matcher = pattern.matcher(ch);

           if (!matcher.matches()) {
                   status3=TRUE; }
           else{
               Toast.makeText(getApplicationContext(),display_match,Toast.LENGTH_LONG).show();
               etPassword.getText().clear();
               etUsername.getText().clear(); } }





}




