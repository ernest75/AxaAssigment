package com.example.ernest.axamobileassigment.screens.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomesDetail;
import com.example.ernest.axamobileassigment.model.City;
import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.networking.GnomesApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivityPresenter implements MainActivityMVP.Presenter {


    private static final String LOG_TAG = MainActivityPresenter.class.getSimpleName();

    MainActivityMVP.View mView;

    Context mContext;

    GnomesApi mGnomesApi;

    public MainActivityPresenter(Context mContext, GnomesApi mGnomesApi) {
        this.mContext = mContext;
        this.mGnomesApi = mGnomesApi;
    }

    @Override
    public void setView(MainActivityMVP.View view) {
        mView = view;
        mView.hideProgressbar();
    }

    @Override
    public void refreshButtonClicked() {
        mView.showProgressbar();
        mGnomesApi.getGnomes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, retrofit2.Response<City> response) {
                Log.e("SERVER RESPONSE : ", response.body().brastlewark.get(0).name);
                mView.showData(response.body().brastlewark);
                mView.hideProgressbar();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.e(LOG_TAG,"Server error");
            }
        });

    }

    @Override
    public void onGnomeCellClicked(Gnome gnome) {
        Intent intent = new Intent(mContext,GnomesDetail.class);
        intent.putExtra(Constants.GNOME_NAME, gnome.name);
        intent.putExtra(Constants.GNOME_ID,gnome.id);
        intent.putExtra(Constants.GNOME_AGE,gnome.age);
        intent.putExtra(Constants.GNOME_WEIGHT,gnome.weight);
        intent.putExtra(Constants.GNOME_HEIGHT,gnome.height);
        intent.putExtra(Constants.GNOME_HAIR_COLOR,gnome.hairColor);
        intent.putStringArrayListExtra(Constants.GNOME_PROFESSIONS, (ArrayList<String>) gnome.professions);
        intent.putStringArrayListExtra(Constants.GNOME_FRIENDS, (ArrayList<String>) gnome.friends);
        intent.putExtra(Constants.GNOME_PICTURE, gnome.thumbnail);
        mContext.startActivity(intent);

    }

}
