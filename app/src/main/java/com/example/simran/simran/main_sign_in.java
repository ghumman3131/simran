package com.example.simran.simran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;




public class main_sign_in extends AppCompatActivity {
    EditText mobile,password;
    CheckBox check1;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        mobile=(EditText)findViewById(R.id.edit_1);
        password=(EditText)findViewById(R.id.edit_2);
        check1=(CheckBox)findViewById(R.id.check_box);
        text1=(TextView) findViewById(R.id.text_1);
        text2=(TextView) findViewById(R.id.text_2);




    } public void gotohomepage( View v) throws JSONException
    {

        String stringmobile=mobile.getText().toString();
        String stringpassword=password.getText().toString();


        if(stringmobile.equals(""))
        {
            Toast.makeText(main_sign_in.this,"please enter your mobile no.",Toast.LENGTH_SHORT).show();
            return;
        }
        if(stringpassword.equals(""))
        {
            Toast.makeText(main_sign_in.this,"please enter your password",Toast.LENGTH_SHORT).show();
            return;

        }


        JSONObject jobj = new JSONObject();

        try {

            jobj.put("mobile_key", stringmobile);
            jobj.put("password_key",stringpassword);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(jobj);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://192.168.0.50/signin.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("done"))


                    {
                        Intent i = new Intent(main_sign_in.this , main_home_page.class);
                        startActivity(i);

                        finish();
                    }

                    else {
                        Toast.makeText(main_sign_in.this,"error try again",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        }
        );

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000,3,2));

        AppController app = new AppController(main_sign_in.this);
        app.addToRequestQueue(jobreq);
    }


    public void gotoSignup(View v){
        Intent i=new Intent(main_sign_in.this,Main_sign_up.class);
        startActivity(i);
    }



}



