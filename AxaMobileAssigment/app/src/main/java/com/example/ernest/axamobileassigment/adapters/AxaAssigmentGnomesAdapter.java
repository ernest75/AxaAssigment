package com.example.ernest.axamobileassigment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ernest.axamobileassigment.R;

import com.example.ernest.axamobileassigment.model.Gnome;

import java.util.List;

/**
 * Created by Ernest on 06/11/2017.
 */

//todo afegir image

public class AxaAssigmentGnomesAdapter extends RecyclerView.Adapter<AxaAssigmentGnomesAdapter.ViewHolder> {


    List<Gnome> mGnomesList;

    public AxaAssigmentGnomesAdapter (List<Gnome> gnomes ){
        mGnomesList = gnomes;
    }
    private LayoutInflater mCursorInflater;

//    public AxaAssigmentGnomesAdapter(Context context, Cursor cursor, int flags) {
//        super(context, cursor, flags);
//        mCursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View v = mCursorInflater.inflate(R.layout.gnome_row, parent, false);
//        return v;
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        TextView tvGnomeName = (TextView) view.findViewById(R.id.tvGnomeName);
//        tvGnomeName.setText(cursor.getString(cursor.getColumnIndex(Gnomes.COLUMN_GNOME_NAME)));
//
//        String imgUrl = cursor.getString(cursor.getColumnIndex(Gnomes.COLUMN_URL_IMAGE));
//        if ((imgUrl != null) && (imgUrl.length() != 0)) {
//            NetworkImageView thumbnail = (NetworkImageView) view.findViewById(R.id.idThumbnail);
//            thumbnail.setImageUrl(imgUrl, VolleySingleton.getInstance(context).getImageLoader());
//
//        }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gnome_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gnome gnome = mGnomesList.get(position);
        holder.mGnomeName.setText(gnome.name);
    }

    @Override
    public int getItemCount() {
        return mGnomesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mGnomeName;

        public ViewHolder(View itemView) {
            super(itemView);
            mGnomeName = itemView.findViewById(R.id.tvGnomeName);
        }
    }
}
