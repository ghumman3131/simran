package com.example.simran.simran;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import signin.php;


public class Main_otp_code extends AppCompatActivity {
    Button btn;
    EditText otp1 , otp2 , otp3 , otp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_code);

        Toast.makeText(Main_otp_code.this,getIntent().getStringExtra("randompin_key"),Toast.LENGTH_SHORT).show();


            otp1 = (EditText) findViewById(R.id.otp_1);
            otp2 = (EditText) findViewById(R.id.otp_2);
            otp3 = (EditText) findViewById(R.id.otp_3);
            otp4 = (EditText) findViewById(R.id.opt_4);

            otp1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.length() == 1)
                    {
                        otp1.clearFocus();
                        otp2.requestFocus();
                        otp2.setCursorVisible(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            otp2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.length() == 1)
                    {
                        otp2.clearFocus();
                        otp3.requestFocus();
                        otp3.setCursorVisible(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            otp3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.length() == 1)
                    {
                        otp3.clearFocus();
                        otp4.requestFocus();
                        otp4.setCursorVisible(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            otp4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.length() == 1)
                    {
                        String value1 = otp1.getText().toString();
                        String value2 = otp2.getText().toString();
                        String value3 = otp3.getText().toString();
                        String value4 = otp4.getText().toString();

                        String final_pin = value1+value2+value3+value4;



                        if(getIntent().getStringExtra("randompin_key").equals(final_pin))
                        {
                            JSONObject jobj = new JSONObject();

                            try {
                                jobj.put("name_key",getIntent().getStringExtra("name_key"));
                                jobj.put("mobile_key",getIntent().getStringExtra("mobile_key"));
                                jobj.put("password_key",getIntent().getStringExtra("password_key"));
                                jobj.put("randompin_key",getIntent().getStringExtra("randompin_key"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ php.abc+"/signup.php", jobj, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {
                                        if(response.getString("key").equals("done"))


                                        {
                                            Intent i = new Intent(Main_otp_code.this,main_home_page.class);
                                            startActivity(i);

                                            finish();
                                        }
                                        if(response.getString("key").equals("not done"))


                                        {
                                            Toast.makeText(Main_otp_code.this,"mobile already exist",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(Main_otp_code.this,"error try again",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {


                                }
                            }
                        );

                            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000,3,2));

                            AppController app = new AppController(Main_otp_code.this);
                            app.addToRequestQueue(jobreq);
                    }
                        else {
                            Toast.makeText(Main_otp_code.this," not done",Toast.LENGTH_SHORT).show();
                        }


                    }
                }

                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });
        }



}
