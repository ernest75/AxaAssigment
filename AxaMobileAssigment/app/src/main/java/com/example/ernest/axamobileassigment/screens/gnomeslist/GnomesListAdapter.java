package com.example.ernest.axamobileassigment.screens.gnomeslist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ernest.axamobileassigment.common.Constants;
import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomesDetail;
import com.example.ernest.axamobileassigment.R;

import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.model.Gnome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ernest on 06/11/2017.
 */


public class GnomesListAdapter extends RecyclerView.Adapter<GnomesListAdapter.ViewHolder>  {



    public interface OnGnomeClickListener {
        void onGnomeClick(Gnome gnome);
    }


    private List<Gnome> mGnomesList;
    private Context mContext;
    public GnomesListAdapter(List<Gnome> gnomes, Context context){
        mGnomesList = gnomes;
        mContext = context;
        //mOnGnomeClickListener = onGnomeClickListener;
        //mRootView =  LayoutInflater.from(context).inflate(R.layout.gnome_row,parent,false);
    }
    private LayoutInflater mCursorInflater;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gnome_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Gnome gnome = mGnomesList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        holder.mGnomeName.setText(gnome.name);
        GlideApp
                .with(mContext)
                .load(gnome.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_file_download)
                .into(holder.mGnomePicture);



    }

    @Override
    public int getItemCount() {
        return mGnomesList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView mGnomeName;
        ImageView mGnomePicture;


        public ViewHolder(View itemView) {
            super(itemView);
            mGnomeName = itemView.findViewById(R.id.tvGnomeName);
            mGnomePicture = itemView.findViewById(R.id.ivGnomePicture);


        }


    }
}
