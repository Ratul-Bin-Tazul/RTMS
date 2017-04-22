package com.drbt.onlinedatacollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.*;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText username,pin;
    Button loginBtn;
    SharedPreferences userLoggedIn;

    public static final String USER_PREF = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!checkUser().equals("no user")) {
            Intent i = new Intent(this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }else {

            username = (EditText) findViewById(R.id.username);
            pin = (EditText) findViewById(R.id.pin);

            loginBtn = (Button) findViewById(R.id.loginBtn);


            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LoginActivity.this, "Checking...", Toast.LENGTH_SHORT).show();

                    HashMap postData = new HashMap();
                    postData.put("txtUsername", username.getText().toString());
                    postData.put("txtPassword", pin.getText().toString());
                    PostResponseAsyncTask login = new PostResponseAsyncTask(LoginActivity.this, postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            SharedPreferences.Editor editor = userLoggedIn.edit();
                            if (s.contains("success")) {

                                String DnsoUsername = username.getText().toString();
                                String pinNumber =  pin.getText().toString();
                                editor.putString("username",DnsoUsername );
                                editor.putString("pin",pinNumber );
                                // Commit the edits!
                                editor.apply();

                                Toast.makeText(LoginActivity.this, "Wellcome " + DnsoUsername, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.putExtra("username",DnsoUsername);
                                startActivity(i);
                                finish();
                            } else {
                                editor.putString("username","no user" );
                                // Commit the edits!
                                editor.apply();
                                Toast.makeText(LoginActivity.this, "Wrong Username or Pin", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    login.execute("https://rafathossain.com/androidlogin.php");
                }
            });
        }
    }

//    public void login(View v) {
//        Toast.makeText(LoginActivity.this,"clicked",Toast.LENGTH_SHORT).show();
//
//        HashMap postData = new HashMap();
//        postData.put("txtUsername",username.getText().toString());
//        postData.put("txtPassword",pin.getText().toString());
//        PostResponseAsyncTask login = new PostResponseAsyncTask(LoginActivity.this,postData, new AsyncResponse() {
//            @Override
//            public void processFinish(String s) {
//                if(s.contains("success")) {
//                    Toast.makeText(LoginActivity.this,"Wellcome "+username.getText().toString(),Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(LoginActivity.this, com.kosalgeek.genasync12.MainActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
//                }else{
//                    Toast.makeText(LoginActivity.this,"Wrong Username or Pin",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        login.execute("https://rafathossain.com/androidlogin.php");
//    }

    public String checkUser() {
        userLoggedIn = getSharedPreferences(USER_PREF, 0);
        //int count = activityCount.getInt("activityCount", 1);
        String username = userLoggedIn.getString("username","no user");

        return username;
    }
}
