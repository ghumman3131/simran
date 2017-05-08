package com.example.simran.simran;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ghumman on 4/30/2017.
 */

public class All_events extends Fragment {

    public RecyclerView recycle ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.all_event_layout , container , false);

        recycle = (RecyclerView) v.findViewById(R.id.recycle_event);

        recycle.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL ,false));


        get_data();
        return v ;
    }

    public void get_data()
    {
        JSONObject job = new JSONObject();

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/get_events.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jarr = response.getJSONArray("result");

                    events_adapter ad = new events_adapter(jarr , getActivity());

                    recycle.setAdapter(ad);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController app = new AppController(getActivity());

        app.addToRequestQueue(jobreq);
    }
}
