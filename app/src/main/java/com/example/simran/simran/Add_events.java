package com.example.simran.simran;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by ghumman on 4/30/2017.
 */

public class Add_events extends Fragment {

    public static EditText event_address ;

    private Button add_event ;

    int year;
    int month;
    int day;
    private EditText dateedt , event_title , event_description;

    public static ImageView event_image ;
    public static String event_image_s ="" ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_event_layout , container , false);

        add_event = (Button) v.findViewById(R.id.add_event);

        event_title = (EditText) v.findViewById(R.id.event_title);
        event_description = (EditText) v.findViewById(R.id.event_description);

        event_address = (EditText) v.findViewById(R.id.event_address);

        event_image = (ImageView) v.findViewById(R.id.event_image);

        event_image.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            123);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant that should be quite unique

                    return;
                }
               getActivity().startActivity(new Intent(getActivity() , Select_event_image.class));
            }
        });

        dateedt = (EditText) v.findViewById(R.id.event_date);

        Calendar c = Calendar.getInstance();
        dateedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        dateedt.setText(new StringBuilder().append(selectedyear).append("/").append(selectedmonth + 1).append("/").append(selectedday));
                       // int month_k = selectedmonth + 1;

                    }
                }, year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date Here
                  mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                mDatePicker.show();

            }
        });

        event_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getActivity() , Place_picker.class));
            }
        });


        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = event_title.getText().toString();
                String description = event_description.getText().toString();
                String address = event_address.getText().toString();
                String date = dateedt.getText().toString();

                address.replace("'","");

                JSONObject job = new JSONObject();
                try {
                    job.put("title_key" , title);
                    job.put("description_key" , description);
                    job.put("address_key" , address);
                    job.put("date_key" , date);
                    job.put("image_key" , event_image_s);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(job);

                JsonObjectRequest jobreq = new JsonObjectRequest("http://192.168.0.50/add_event.php", job, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("done"))
                            {
                                Toast.makeText(getActivity() , "event added" , Toast.LENGTH_SHORT).show();

                            }

                            else  {

                                Toast.makeText(getActivity() , "error" , Toast.LENGTH_SHORT).show();

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

                jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

                AppController app = new AppController(getActivity());

                app.addToRequestQueue(jobreq);






            }
        });

        return v ;
    }
}
