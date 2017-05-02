package com.example.simran.simran;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        Handler hn=new Handler();
        hn.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(startup.this,main_sign_in.class);
                startActivity(i);
                finish();
            }
        },3000);




    }
}
