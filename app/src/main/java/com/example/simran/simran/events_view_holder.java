package com.example.simran.simran;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ghumman on 5/2/2017.
 */

public class events_view_holder extends RecyclerView.ViewHolder {

    public TextView event_by , event_title  , event_address ;

    public ImageView event_image ;
    public events_view_holder(View itemView) {
        super(itemView);

        event_by = (TextView) itemView.findViewById(R.id.by_name);
        event_title = (TextView) itemView.findViewById(R.id.event_title);
        event_address = (TextView) itemView.findViewById(R.id.event_address);

        event_image = (ImageView) itemView.findViewById(R.id.event_image);

    }
}
