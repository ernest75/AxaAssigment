package com.example.ernest.axamobileassigment.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.ernest.axamobileassigment.R;

import com.example.ernest.axamobileassigment.database.AxaAssigmentContract.*;
import com.example.ernest.axamobileassigment.volleyhelper.VolleySingleton;

/**
 * Created by Ernest on 06/11/2017.
 */

public class AxaAssigmentGnomesAdapter extends CursorAdapter {


    private LayoutInflater mCursorInflater;

    public AxaAssigmentGnomesAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        mCursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mCursorInflater.inflate(R.layout.gnome_row, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvGnomeName = (TextView) view.findViewById(R.id.tvGnomeName);
        tvGnomeName.setText(cursor.getString(cursor.getColumnIndex(Gnomes.COLUMN_GNOME_NAME)));

        String imgUrl = cursor.getString(cursor.getColumnIndex(Gnomes.COLUMN_URL_IMAGE));
        if ((imgUrl != null) && (imgUrl.length() != 0)) {
            NetworkImageView thumbnail = (NetworkImageView) view.findViewById(R.id.idThumbnail);
            thumbnail.setImageUrl(imgUrl, VolleySingleton.getInstance(context).getImageLoader());

        }


    }

}
