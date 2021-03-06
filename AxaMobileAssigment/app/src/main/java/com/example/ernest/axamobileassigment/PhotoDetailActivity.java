package com.example.ernest.axamobileassigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.screens.common.activities.BaseActivity;

import javax.inject.Inject;

public class PhotoDetailActivity extends BaseActivity {

//todo comprobar animacio
    private static final String LOG_TAG = "PhotoDetailActivity";
    ImageView ivGnomePicture;

    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        getPresentationComponent().inject(this);

        ivGnomePicture = findViewById(R.id.ivGnomePhoto);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG_TAG, "ON RESUME IS CALLED");
        Intent intent = getIntent();
        String urlImage = intent.getStringExtra(Constants.GNOME_PICTURE);
        GlideApp
                .with(mContext)
                .load(urlImage)
                .centerCrop()
                .transition(GenericTransitionOptions.with(R.anim.zoom_in))
                .into(ivGnomePicture);

    }

    @Override
    protected void onDestroy() {
        Log.e(LOG_TAG, "ON DESTROY IS CALLED");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        ivGnomePicture.setVisibility(View.GONE);
        finish();
        super.onBackPressed();

    }
}
