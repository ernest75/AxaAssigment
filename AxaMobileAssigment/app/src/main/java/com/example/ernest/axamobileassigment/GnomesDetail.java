package com.example.ernest.axamobileassigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ernest.axamobileassigment.adapters.SimpleRecyclerAdapter;
import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.screens.common.activities.BaseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.inject.Inject;

public class GnomesDetail extends BaseActivity {

    //variables member
    private static final String LOG_TAG = GnomesDetail.class.getSimpleName() ;
    int mIdLocal;
    TextView mTvAge,mTvName,mTvWeight,mTvHeight,mTvHairColor;
    RecyclerView mRvProfessions, mRvGnomeFriends;
    ImageView mIvGnomePicture;
//    @Inject
//    RpgGameModel mRpgGameModel;
    ArrayList<String> mProfessionsArray, mFriendsArray;

    @Inject
    Context mContext;

    SimpleRecyclerAdapter mProfessionsAdapter, mFriendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gnome_detail_improve);

        getPresentationComponent().inject(this);



        //mRpgGameModel= RpgGameModel.getInstance();

        //Getting references
        mTvName = findViewById(R.id.tvGnomeName);
        mTvAge = findViewById(R.id.tvGnomeAge);
        mTvWeight = findViewById(R.id.tvGnomeWeight);
        mTvHeight = findViewById(R.id.tvGnomeHeight);
        mTvHairColor = findViewById(R.id.tvGnomeHairColor);
        mRvProfessions = findViewById(R.id.rvProfesions);
        mRvGnomeFriends = findViewById(R.id.rvFriends);
        mIvGnomePicture = findViewById(R.id.ivGnomeImage);

//        mProfessionsArray = new ArrayList<>();
//        mFriendsArray = new ArrayList<>();

        Intent intent = getIntent();
        //String name = intent.getStringExtra(Constants.GNOME_NAME);
        //String age = intent.getStringExtra(Constants.GNOME_AGE);
        double weight = intent.getDoubleExtra(Constants.GNOME_WEIGHT,-1);
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        //todo limitar decimals
        double height = intent.getDoubleExtra(Constants.GNOME_HEIGHT,-1);
        String hairColor = intent.getStringExtra(Constants.GNOME_HAIR_COLOR);
        String urlImage = intent.getStringExtra(Constants.GNOME_PICTURE);
        mProfessionsArray = intent.getStringArrayListExtra(Constants.GNOME_PROFESSIONS);
        mFriendsArray = intent.getStringArrayListExtra(Constants.GNOME_FRIENDS);
        mTvName.setText(intent.getStringExtra(Constants.GNOME_NAME));
        mTvAge.setText(intent.getIntExtra(Constants.GNOME_AGE,-1)+"");
        mTvWeight.setText(numberFormat.format(weight) + "");
        mTvHeight.setText(numberFormat.format(height )+ "");
        mTvHairColor.setText(hairColor);
        GlideApp
                .with(mContext)
                .load(urlImage)
                .centerCrop()
                .into(mIvGnomePicture);

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager horizontalLayoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mProfessionsAdapter = new SimpleRecyclerAdapter(this,mProfessionsArray);
        mRvProfessions.setLayoutManager(horizontalLayoutManager);
        mRvProfessions.setAdapter(mProfessionsAdapter);

        mFriendsAdapter = new SimpleRecyclerAdapter(this,mFriendsArray);
        mRvGnomeFriends.setLayoutManager(horizontalLayoutManager2);
        mRvGnomeFriends.setAdapter(mFriendsAdapter);


//        ArrayAdapter<String> arrayAdapterProfessions = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                mProfessionsArray );
//
//        mRvProfessions.setAdapter(arrayAdapterProfessions);
//        ArrayAdapter<String> arrayAdapterFreinds = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                mFriendsArray);
//
//        mRvGnomeFriends.setAdapter(arrayAdapterFreinds);

        //todo millorar friends i professions pque es destingeixi que son llistes i que facin fit un cop acabades

   }

}
