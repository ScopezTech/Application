package com.example.my_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidhire.splashscreen.R;
public class Login_Activity extends AppCompatActivity implements View.OnClickListener{
    Button btnlogin;
    EditText etUsername ,etPassword;
    TextView registerLink;
    String JSONresult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        registerLink = (TextView) findViewById(R.id.registerLink);
        registerLink.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        JSONresult = Utils.getJSONFromAssets(this);
        // You need to read user.txt and assign the string to JSONresult
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnlogin:
               doLogin(etUsername.getText().toString(),etPassword.getText().toString());
                break;
            case R.id.registerLink:
                startActivity(new Intent(this, register.class));
                break;
        }

    }
    private void doLogin(String userName,String password){
        if (Utils.checkLoginCredentials(JSONresult, userName,password)){
            startActivity(new Intent(this, Dashboard.class));
        }
        else{
            // Add error message
        }
    }
}
