package com.example.ernest.axamobileassigment;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.ernest.axamobileassigment.adapters.AxaAssigmentGnomesAdapter;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract;
import com.example.ernest.axamobileassigment.database.AxaAssigmentDbSqlite;
import com.example.ernest.axamobileassigment.model.City;
import com.example.ernest.axamobileassigment.model.RpgGameModel;
import com.example.ernest.axamobileassigment.networking.GnomesApi;
import com.example.ernest.axamobileassigment.screens.common.activities.BaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    RecyclerView mRvGnomes;

    Button mBtnGetGnomes;

    @Inject
    Context mContext;

    @Inject
    RpgGameModel mRpgGameModel;

    @Inject
    GnomesApi mGnomesApi;

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inject dependencies
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
                getGnomesFromServer(mGnomesApi);
            }
        });


    }

    public void getGnomesFromServer(GnomesApi gnomesApi) {

        //gnomesApi = mGnomesApi;
        gnomesApi.getGnomes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, retrofit2.Response<City> response) {
                Log.e("SERVER RESPONSE : ", response.body().brastlewark.get(0).name);
                mRvGnomes.setLayoutManager(new LinearLayoutManager(mContext));
                mRvGnomes.setAdapter(new AxaAssigmentGnomesAdapter(response.body().brastlewark, mContext));
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.e(LOG_TAG,"Server error");
            }
        });
    }



}
