package com.example.ernest.axamobileassigment.screens.gnomeslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.ernest.axamobileassigment.R;
import com.example.ernest.axamobileassigment.model.City;
import com.example.ernest.axamobileassigment.networking.GnomesApi;
import com.example.ernest.axamobileassigment.screens.common.BaseActivity;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class GnomesList extends BaseActivity {

    private static final String LOG_TAG = GnomesList.class.getSimpleName();

    RecyclerView mRvGnomes;

    Button mBtnGetGnomes;

    @Inject
    Context mContext;

    @Inject
    GnomesApi mGnomesApi;

    ProgressBar mProgressBar;
    private GnomesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getPresentationComponent().inject(this);

        //getting references
        mRvGnomes = findViewById(R.id.lvGnomes);
        mBtnGetGnomes = (Button)findViewById(R.id.btnGetGnomes);
        mProgressBar = (ProgressBar)findViewById(R.id.pbWaitingCircle);

        mProgressBar.setVisibility(View.GONE);

        mBtnGetGnomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                getGnomesFromServer();
            }
        });


    }


    public void getGnomesFromServer() {

        mGnomesApi.getGnomes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, retrofit2.Response<City> response) {
                Log.e("SERVER RESPONSE : ", response.body().brastlewark.get(0).name);
                mRvGnomes.setLayoutManager(new LinearLayoutManager(mContext));
                mRvGnomes.setAdapter(new GnomesListAdapter(response.body().brastlewark, mContext));
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.e(LOG_TAG,"Server error");
            }
        });
    }






    //need to handle permissions to be able to copy the db on external storage
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(GnomesList.this, new String[]{WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_ID);
//    }

}
