package com.example.ernest.axamobileassigment.screens.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.ernest.axamobileassigment.R;
import com.example.ernest.axamobileassigment.adapters.AxaAssigmentGnomesAdapter;
import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.model.RpgGameModel;
import com.example.ernest.axamobileassigment.networking.GnomesApi;
import com.example.ernest.axamobileassigment.screens.common.activities.BaseActivity;


import java.util.List;

import javax.inject.Inject;

//todo handle memory leaks



public class MainActivity extends BaseActivity implements MainActivityMVP.View {

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

    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //inject dependencies
        getPresentationComponent().inject(this);

        //getting references
        mRvGnomes = findViewById(R.id.lvGnomes);
        mBtnGetGnomes = findViewById(R.id.btnGetGnomes);
        mProgressBar = findViewById(R.id.pbWaitingCircle);

        //instantiations
        mPresenter = new MainActivityPresenter(mContext,mGnomesApi);
        mPresenter.setView(this);

        mBtnGetGnomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.refreshButtonClicked();
            }
        });

    }

    @Override
    public void showData(List<Gnome> gnomes) {
        mRvGnomes.setLayoutManager(new LinearLayoutManager(mContext));
        mRvGnomes.setAdapter(new AxaAssigmentGnomesAdapter(gnomes, mContext, mPresenter));
    }

    @Override
    public void showProgressbar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        mProgressBar.setVisibility(View.GONE);
    }


}
