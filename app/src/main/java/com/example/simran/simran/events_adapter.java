package com.example.simran.simran;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ghumman on 5/2/2017.
 */

public class events_adapter extends RecyclerView.Adapter<events_view_holder> {

    JSONArray jarr ;
    Activity a ;

    public events_adapter(JSONArray jarr , Activity a)
    {
        this.jarr = jarr ;
        this.a = a ;
    }
    @Override
    public events_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new events_view_holder(LayoutInflater.from(a).inflate(R.layout.all_event_cell,parent , false));

    }

    @Override
    public void onBindViewHolder(events_view_holder holder, int position)
    {

        try {
            JSONObject job = jarr.getJSONObject(position);
            holder.event_by.setText(job.getString("name"));
            holder.event_title.setText(job.getString("name"));
            holder.event_address.setText(job.getString("location"));

            holder.event_image.setImageBitmap(StringToBitMap(job.getString("image")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jarr.length();
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
