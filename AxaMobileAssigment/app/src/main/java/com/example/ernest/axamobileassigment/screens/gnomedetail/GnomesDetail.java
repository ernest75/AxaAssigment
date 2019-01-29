package com.example.ernest.axamobileassigment.screens.gnomedetail;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ernest.axamobileassigment.PhotoDetailActivity;
import com.example.ernest.axamobileassigment.R;
import com.example.ernest.axamobileassigment.adapters.SimpleRecyclerAdapter;
import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.glide.GlideApp;
import com.example.ernest.axamobileassigment.model.GnomeApp;
import com.example.ernest.axamobileassigment.screens.common.activities.BaseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.inject.Inject;

public class GnomesDetail extends BaseActivity implements GnomeDetailMVP.View {

    //variables member
    private static final String LOG_TAG = GnomesDetail.class.getSimpleName() ;
    TextView mTvAge,mTvName,mTvWeight,mTvHeight,mTvHairColor;
    RecyclerView mRvProfessions, mRvGnomeFriends;
    ImageView mIvGnomePicture;

    ArrayList<String> mProfessionsArray, mFriendsArray;

    @Inject
    Context mContext;

    @Inject
    GnomeDetailMVP.Presenter mPresenter;

    SimpleRecyclerAdapter mProfessionsAdapter, mFriendsAdapter;

    private Intent mIntent;

    private GnomeApp mGnomeRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gnome_detail_improve);

        getPresentationComponent().inject(this);

        mIntent = getIntent();

        mPresenter.setView(this);

        mGnomeRepo = mPresenter.getCurrentGnome();

        //todo millorar friends i professions pque es destingeixi que son llistes i que facin fit un cop acabades
        //todo fer zoom a la imatge amb dialog
   }

    @Override
    protected void onResume() {
        super.onResume();

        //Getting references
        mTvName = findViewById(R.id.tvGnomeName);
        mTvAge = findViewById(R.id.tvGnomeAge);
        mTvWeight = findViewById(R.id.tvGnomeWeight);
        mTvHeight = findViewById(R.id.tvGnomeHeight);
        mTvHairColor = findViewById(R.id.tvGnomeHairColor);
        mRvProfessions = findViewById(R.id.rvProfesions);
        mRvGnomeFriends = findViewById(R.id.rvFriends);
        mIvGnomePicture = findViewById(R.id.ivGnomeImage);

        showData();

        Pair<View,String> pair = new Pair<View, String>(mIvGnomePicture,"transition_gnome_photo");
        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pair);

        mIvGnomePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PhotoDetailActivity.class);
                intent.putExtra(Constants.GNOME_PICTURE,mGnomeRepo.urlImage);
                startActivity(intent,options.toBundle());

            }
        });

    }

    @Override
    public void showData() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        mProfessionsArray = mGnomeRepo.professions;
        mFriendsArray = mGnomeRepo.friends;
        mTvName.setText(mGnomeRepo.name);
        mTvAge.setText(mGnomeRepo.age + "");
        mTvWeight.setText(numberFormat.format(mGnomeRepo.weight) + "");
        mTvHeight.setText(numberFormat.format(mGnomeRepo.height )+ "");
        mTvHairColor.setText(mGnomeRepo.hairColor);
        GlideApp
                .with(mContext)
                .load(mGnomeRepo.urlImage)
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
    }

    @Override
    public Intent getIntentCreator() {
        return mIntent;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
