package com.example.ernest.axamobileassigment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract.*;
import com.example.ernest.axamobileassigment.model.RpgGameModel;
import com.example.ernest.axamobileassigment.screens.common.activities.BaseActivity;
import com.example.ernest.axamobileassigment.volleyhelper.VolleySingleton;

import java.util.ArrayList;

import javax.inject.Inject;

public class GnomesDetail extends BaseActivity {

    //variables member
    private static final String LOG_TAG = GnomesDetail.class.getSimpleName() ;
    int mIdLocal;
    TextView mTvAge,mTvName,mTvWeight,mTvHeight,mTvHairColor;
    ListView mLvProfessions, mLvGnomeFriends;
    NetworkImageView mNiGnomePicture;
    @Inject
    RpgGameModel mRpgGameModel;
    ArrayList<String> mProfessionsArray, mFriendsArray;

    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gnome_detail);

        getPresentationComponent().inject(this);

        //mRpgGameModel= RpgGameModel.getInstance();

        //Getting references
        mTvName = (TextView)findViewById(R.id.tvGnomeName);
        mTvAge = (TextView)findViewById(R.id.tvGnomeAge);
        mTvWeight = (TextView)findViewById(R.id.tvGnomeWeight);
        mTvHeight = (TextView)findViewById(R.id.tvGnomeHeight);
        mTvHairColor = (TextView)findViewById(R.id.tvGnomeHairColor);
        mLvProfessions =(ListView )findViewById(R.id.lvProfessions);
        mLvGnomeFriends=(ListView )findViewById(R.id.lvFriends);
        mNiGnomePicture = (NetworkImageView)findViewById(R.id.ivGnomeImage);

        mProfessionsArray = new ArrayList<>();
        mFriendsArray = new ArrayList<>();

        Intent intent = getIntent();
        mIdLocal = intent.getIntExtra("id_local", -1);
        if (mIdLocal == -1) {
            finish();
        }

        //Cursors to retrieve info from Db Sqlite

        //Gnome info
        Cursor cGnomeInfo = mRpgGameModel.getGnomeInfo(mIdLocal,mContext);

        //Gnome professions
        Cursor cGnomeProfessions = mRpgGameModel.getGnomeProfessions(mIdLocal,mContext);

        //Gnome Friends
        Cursor cGnomeFriendsNames = mRpgGameModel.getGnomeFriends(mIdLocal,mContext);


        //Getting info from cursors

        //Info from Gnome table
        cGnomeInfo.moveToFirst();
        String name = cGnomeInfo.getString(cGnomeInfo.getColumnIndex(Gnomes.COLUMN_GNOME_NAME));
        String age = cGnomeInfo.getString(cGnomeInfo.getColumnIndex(Gnomes.COLUMN_AGE));
        String weight = cGnomeInfo.getString(cGnomeInfo.getColumnIndex(Gnomes.COLUMN_WEIGHT));
        String height = cGnomeInfo.getString(cGnomeInfo.getColumnIndex(Gnomes.COLUMN_HEIGHT));
        String hairColor = cGnomeInfo.getString(cGnomeInfo.getColumnIndex(Gnomes.COLUMN_HAIR_COLOR));
        String urlImage = cGnomeInfo.getString(cGnomeInfo.getColumnIndex(Gnomes.COLUMN_URL_IMAGE));

        mTvName.setText(name);
        mTvAge.setText(age);
        mTvWeight.setText(weight);
        mTvHeight.setText(height);
        mTvHairColor.setText(hairColor);
        mNiGnomePicture.setImageUrl(urlImage, VolleySingleton.getInstance(mContext).getImageLoader());

        //Info Gnome Professions
        if(cGnomeProfessions.getCount()>0) {
            cGnomeProfessions.moveToFirst();
            String professionName = cGnomeProfessions.getString(cGnomeProfessions.getColumnIndex(Professions.COLUMN_PROFESION_NAME));
            mProfessionsArray.add(professionName);
            while (cGnomeProfessions.moveToNext()) {
                professionName = cGnomeProfessions.getString(cGnomeProfessions.getColumnIndex(Professions.COLUMN_PROFESION_NAME));
                mProfessionsArray.add(professionName);
            }
        }

        ArrayAdapter<String> arrayAdapterProfessions = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mProfessionsArray );

        mLvProfessions.setAdapter(arrayAdapterProfessions);
        cGnomeProfessions.close();

        //Info gnome freinds
        if(cGnomeFriendsNames.getCount()>0){
            cGnomeFriendsNames.moveToFirst();
            String gnomeFriend = cGnomeFriendsNames.getString(cGnomeFriendsNames.getColumnIndex(Gnomes.COLUMN_GNOME_NAME));
            mFriendsArray.add(gnomeFriend);
            while(cGnomeFriendsNames.moveToNext()){
                gnomeFriend = cGnomeFriendsNames.getString(cGnomeFriendsNames.getColumnIndex(Gnomes.COLUMN_GNOME_NAME));
                mFriendsArray.add(gnomeFriend);
            }
        }

        ArrayAdapter<String> arrayAdapterFreinds = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mFriendsArray);

        mLvGnomeFriends.setAdapter(arrayAdapterFreinds);

    }

}
