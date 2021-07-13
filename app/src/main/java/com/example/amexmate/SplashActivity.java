package com.example.amexmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean ischeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences =  getSharedPreferences("login", Context.MODE_PRIVATE);
        ischeck=sharedPreferences.getBoolean("alreadylogin",false);

        Log.e("value", String.valueOf(ischeck));


        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    if(ischeck==true)
                    {
                        Intent in=new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(in);
                        finish();
                    }

                    else {
                        // After 5 seconds redirect to another intent
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);

                        //Remove activity
                        finish();
                    }
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}