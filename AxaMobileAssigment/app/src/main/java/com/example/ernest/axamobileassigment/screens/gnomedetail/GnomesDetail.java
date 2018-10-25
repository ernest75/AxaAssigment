package com.example.ernest.axamobileassigment.screens.gnomedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ernest.axamobileassigment.R;
import com.example.ernest.axamobileassigment.common.Constants;
import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.screens.common.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class GnomesDetail extends BaseActivity {

    //variables member
    private static final String LOG_TAG = GnomesDetail.class.getSimpleName() ;
    int mIdLocal;
    TextView mTvAge,mTvName,mTvWeight,mTvHeight,mTvHairColor;
    ListView mLvProfessions, mLvGnomeFriends;
    ImageView mIvGnomePicture;
//    @Inject
//    RpgGameModel mRpgGameModel;
    ArrayList<String> mProfessionsArray, mFriendsArray;

    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gnome_detail);

        getPresentationComponent().inject(this);


        //Getting references
        mTvName = findViewById(R.id.tvGnomeName);
        mTvAge = findViewById(R.id.tvGnomeAge);
        mTvWeight = findViewById(R.id.tvGnomeWeight);
        mTvHeight = findViewById(R.id.tvGnomeHeight);
        mTvHairColor = findViewById(R.id.tvGnomeHairColor);
        mLvProfessions = findViewById(R.id.lvProfessions);
        mLvGnomeFriends= findViewById(R.id.lvFriends);
        mIvGnomePicture = findViewById(R.id.ivGnomeImage);

//        mProfessionsArray = new ArrayList<>();
//        mFriendsArray = new ArrayList<>();

        Intent intent = getIntent();
        //String name = intent.getStringExtra(Constants.GNOME_NAME);
        //String age = intent.getStringExtra(Constants.GNOME_AGE);
        double weight = intent.getDoubleExtra(Constants.GNOME_WEIGHT,-1);
        double height = intent.getDoubleExtra(Constants.GNOME_HEIGHT,-1);
        String hairColor = intent.getStringExtra(Constants.GNOME_HAIR_COLOR);
        String urlImage = intent.getStringExtra(Constants.GNOME_PICTURE);
        mProfessionsArray = intent.getStringArrayListExtra(Constants.GNOME_PROFESSIONS);
        mFriendsArray = intent.getStringArrayListExtra(Constants.GNOME_FRIENDS);
        mTvName.setText(intent.getStringExtra(Constants.GNOME_NAME));
        mTvAge.setText(intent.getIntExtra(Constants.GNOME_AGE,-1)+"");
        mTvWeight.setText(weight + "");
        mTvHeight.setText(height + "");
        mTvHairColor.setText(hairColor);
        GlideApp
                .with(mContext)
                .load(urlImage)
                .centerCrop()
                .into(mIvGnomePicture);


        ArrayAdapter<String> arrayAdapterProfessions = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mProfessionsArray );

        mLvProfessions.setAdapter(arrayAdapterProfessions);
        ArrayAdapter<String> arrayAdapterFriends = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mFriendsArray);

        mLvGnomeFriends.setAdapter(arrayAdapterFriends);

   }

}
