package com.example.ernest.axamobileassigment.model;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.ernest.axamobileassigment.networking.GnomesApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class GnomeRepository {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ArrayList<Gnome> mGnomes = new ArrayList<>();
    private Observable<City> mGnomesResponseObservable;
    private GnomesApi mGnomeApi;


    public GnomeRepository(GnomesApi gnomesApi) {
        this.mGnomeApi = gnomesApi;

    }


    public Observable<List<Gnome>> getGnomesFromNetwork(){

        Observable<City> cityResultFromNetwork = mGnomeApi.getGnomesRx();

        Observable<List<Gnome>> gnomeResultsFromNetwork= cityResultFromNetwork.flatMap(new Function<City, Observable<List<Gnome>>>() {
           @Override
           public Observable<List<Gnome>> apply(City city) throws Exception {
               return Observable.fromArray(city.brastlewark);
           }
       });

        return gnomeResultsFromNetwork;

    }
}
