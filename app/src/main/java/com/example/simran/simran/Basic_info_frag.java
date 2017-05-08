package com.example.simran.simran;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ghumman on 5/3/2017.
 */

public class Basic_info_frag extends Fragment {

    EditText email , mobile , name ;

    RadioButton male_radio , female_radio ;

    Button update ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basic_info_layout , container , false);

        email = (EditText) v.findViewById(R.id.email);
        mobile = (EditText) v.findViewById(R.id.mobile);
        name = (EditText) v.findViewById(R.id.name);

        update = (Button) v.findViewById(R.id.update_info);

        male_radio = (RadioButton) v.findViewById(R.id.male_radio);
        female_radio = (RadioButton) v.findViewById(R.id.female_radio);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update_info();
            }
        });
        get_basic_info();

        return v;
    }

    public void get_basic_info()
    {
        JSONObject job = new JSONObject();

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/events/get_basic_info.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jobj = response.getJSONObject("result");

                    email.setText(jobj.getString("email"));
                    mobile.setText(jobj.getString("mobile"));
                    name.setText(jobj.getString("name"));
                    if(jobj.getString("gender").equals("male"))
                    {
                        male_radio.setChecked(true);
                    }
                    else if(jobj.getString("gender").equals("female"))
                    {
                        female_radio.setChecked(true);
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
        });

        AppController app = new AppController(getActivity());

        app.addToRequestQueue(jobreq);
    }

    public void update_info()
    {
        JSONObject job = new JSONObject();

        try {
            job.put("name_key" , name.getText().toString());
            job.put("email_key" , email.getText().toString());
            job.put("mobile_key" , mobile.getText().toString());
            if(male_radio.isChecked())
            {
                job.put("gender_key" , "male");
            }
            if (female_radio.isChecked())
            {
                job.put("gender_key" , "female");
            }

            job.put("user_id" , 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/events/update_basic_info.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("done"))
                    {
                        get_basic_info();
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
        });

        AppController app = new AppController(getActivity());

        app.addToRequestQueue(jobreq);

    }
}
