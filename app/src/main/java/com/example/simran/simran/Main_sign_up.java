package com.example.simran.simran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Main_sign_up extends AppCompatActivity {
EditText name,mobile,password,confirm;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        name=(EditText)findViewById(R.id.edit_1);
        mobile=(EditText)findViewById(R.id.edit_2);
        password =(EditText)findViewById(R.id.edit_3);
        confirm=(EditText)findViewById(R.id.edit_4);

    }

    public void gotosignup( View v) throws JSONException
    {
        String stringname=name.getText().toString();
        String stringmobile=mobile.getText().toString();
        String stringpassword=password.getText().toString();
        String stringconfirm=confirm.getText().toString();

        if(stringname.equals(""))
        {
            Toast.makeText(Main_sign_up.this,"please enter your name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(stringmobile.equals(""))
        {
            Toast.makeText(Main_sign_up.this,"please enter your mobile.no",Toast.LENGTH_SHORT).show();
            return;
        }
        if(stringpassword.equals(""))
        {
            Toast.makeText(Main_sign_up.this,"please enter your password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(stringconfirm.equals(""))
        {
            Toast.makeText(Main_sign_up.this,"please confirm your password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!stringconfirm.equals(stringpassword))
        {
            Toast.makeText(Main_sign_up.this,"password do not match",Toast.LENGTH_SHORT).show();
            return;
        }


        int randomPin = (int) (((Math.random())*9000)+1000);

        Intent i = new Intent(Main_sign_up.this , Main_otp_code.class);
        i.putExtra("name_key",stringname);
        i.putExtra("mobile_key",stringmobile);
        i.putExtra("password_key",stringpassword);
        i.putExtra("randompin_key",String.valueOf(randomPin));

        startActivity(i);



    }




}

