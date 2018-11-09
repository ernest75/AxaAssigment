package com.example.ernest.axamobileassigment.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.example.ernest.axamobileassigment.Constants;
import com.example.ernest.axamobileassigment.GnomesDetail;
import com.example.ernest.axamobileassigment.ItemClickListener;
import com.example.ernest.axamobileassigment.R;

import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.model.Gnome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ernest on 06/11/2017.
 */


public class AxaAssigmentGnomesAdapter extends RecyclerView.Adapter<AxaAssigmentGnomesAdapter.ViewHolder>  {


    private List<Gnome> mGnomesList;
    private Context mContext;
    public AxaAssigmentGnomesAdapter(List<Gnome> gnomes, Context context){
        mGnomesList = gnomes;
        mContext = context;
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
        final Gnome gnome = mGnomesList.get(position);
        holder.mGnomeName.setText(gnome.name);
        GlideApp
                .with(mContext)
                .load(gnome.thumbnail)
                .placeholder(R.drawable.ic_file_download)
                .centerCrop()
                .into(holder.mGnomePicture);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(mContext,GnomesDetail.class);
                intent.putExtra(Constants.GNOME_NAME, gnome.name);
                intent.putExtra(Constants.GNOME_AGE,gnome.age);
                intent.putExtra(Constants.GNOME_WEIGHT,gnome.weight);
                intent.putExtra(Constants.GNOME_HEIGHT,gnome.height);
                intent.putExtra(Constants.GNOME_HAIR_COLOR,gnome.hairColor);
                intent.putStringArrayListExtra(Constants.GNOME_PROFESSIONS, (ArrayList<String>) gnome.professions);
                intent.putStringArrayListExtra(Constants.GNOME_FRIENDS, (ArrayList<String>) gnome.friends);
                intent.putExtra(Constants.GNOME_PICTURE, gnome.thumbnail);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGnomesList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView mGnomeName, mGnomeAge, mGnomeHairColor, mGnomeWeight, mGnomeHeight;

        ImageView mGnomePicture;
        public ItemClickListener mItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            mGnomeName = itemView.findViewById(R.id.tvGnomeName);
            mGnomeAge = itemView.findViewById(R.id.tvGnomeAge);
            mGnomeHairColor = itemView.findViewById(R.id.tvGnomeHairColor);
            mGnomeWeight = itemView.findViewById(R.id.tvGnomeWeight);
            mGnomeHeight = itemView.findViewById(R.id.tvGnomeHeight);
            mGnomePicture = itemView.findViewById(R.id.ivGnomePicture);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            mItemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            mItemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
