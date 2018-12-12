package com.example.ernest.axamobileassigment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ernest.axamobileassigment.screens.common.ItemClickListener;
import com.example.ernest.axamobileassigment.R;

import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.screens.main.MainActivityPresenter;

import java.util.List;

/**
 * Created by Ernest on 06/11/2017.
 */


public class AxaAssigmentGnomesAdapter extends RecyclerView.Adapter<AxaAssigmentGnomesAdapter.ViewHolder>  {

    private List<Gnome> mGnomesList;
    private Context mContext;
    private MainActivityPresenter mPresenter;
    public AxaAssigmentGnomesAdapter(List<Gnome> gnomes, Context context, MainActivityPresenter presenter){
        mGnomesList = gnomes;
        mContext = context;
        mPresenter = presenter;
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
                mPresenter.onGnomeCellClicked(gnome);
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
