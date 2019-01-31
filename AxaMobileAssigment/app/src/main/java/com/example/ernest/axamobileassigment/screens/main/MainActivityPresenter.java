package com.example.ernest.axamobileassigment.screens.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract;
import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomesDetail;
import com.example.ernest.axamobileassigment.model.City;
import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.networking.GnomesApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivityPresenter implements MainActivityMVP.Presenter {

    //todo testing

    private static final String LOG_TAG = MainActivityPresenter.class.getSimpleName();

    MainActivityMVP.View mView;

    Context mContext;

    GnomesApi mGnomesApi;

    List<Gnome> gnomes;

    MainModel mMainModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DisposableObserver<List<Gnome>> observable;


    public MainActivityPresenter(Context mContext, GnomesApi mGnomesApi, MainModel mainModel) {
        this.mContext = mContext;
        this.mGnomesApi = mGnomesApi;
        this.mMainModel = mainModel;
    }

    @Override
    public void setView(MainActivityMVP.View view) {
        mView = view;
        mView.hideProgressbar();
    }

    @Override
    public void refreshButtonClicked() {
        mView.showProgressbar();
        compositeDisposable.add(mMainModel.getGnomesObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Gnome>>() {
                    @Override
                    public void onNext(List<Gnome> gnomes) {
                        mView.showData(gnomes);
                        mView.hideProgressbar();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
            );


    }

    //todo acabar de perfilar composittedisposable.clear
    //todo fer query a db i passar per intent o mirar superlink

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

    @Override
    public void clearStreams() {
        mMainModel.clearStreams();
        compositeDisposable.clear();
    }

    @Override
    public void getInfoFromDb() {

        mView.showProgressbar();
        compositeDisposable.add(mMainModel.getGnomesFromDb()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Gnome>>() {
                    @Override
                    public void onNext(List<Gnome> gnomes) {
                        mView.showData(gnomes);
                        mView.hideProgressbar();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );

    }

}
