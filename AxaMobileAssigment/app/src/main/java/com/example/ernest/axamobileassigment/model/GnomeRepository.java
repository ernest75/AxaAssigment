package com.example.ernest.axamobileassigment.model;


import android.util.Log;

import com.example.ernest.axamobileassigment.networking.GnomesApi;
import com.example.ernest.axamobileassigment.room.GnomeDAO;
import com.example.ernest.axamobileassigment.room.entity.GnomeEntity;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class GnomeRepository {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ArrayList<Gnome> mGnomesArrayList = new ArrayList<>();
    private Observable<City> mGnomesResponseObservable;
    private GnomesApi mGnomeApi;
    private GnomeDAO mGnomeDAO;
    private String LOG_TAG = GnomeRepository.class.getSimpleName();
    private DisposableObserver<List<Gnome>> observable;


    @Inject
    public GnomeRepository(GnomesApi gnomesApi, GnomeDAO gnomeDAO) {
        this.mGnomeApi = gnomesApi;
        this.mGnomeDAO = gnomeDAO;

    }


    public Observable<List<Gnome>> getGnomesFromNetwork() {
        deleteAllRows();
        Observable<City> cityResultFromNetwork = mGnomeApi.getGnomesRx();

        Observable<List<Gnome>> gnomeResultsFromNetwork = cityResultFromNetwork.flatMap(new Function<City, Observable<List<Gnome>>>() {
            @Override
            public Observable<List<Gnome>> apply(City city) throws Exception {

                insertToDb(GnomeRepository.this.convertGnomeListToGnomeEntityList(city.brastlewark));
                return Observable.fromArray(city.brastlewark);
            }
        });
        return gnomeResultsFromNetwork;

    }

    public Flowable<List<Gnome>> getGnomesFromDb() {
        return mGnomeDAO.getGnomes()
                .concatMap(new Function<List<GnomeEntity>, Flowable<List<Gnome>>>() {
                    @Override
                    public Flowable<List<Gnome>> apply(List<GnomeEntity> gnomeEntityList) throws Exception {
                        return Flowable.fromArray(convertGnomeEntityListToGnomeList(gnomeEntityList));
                    }
                });

    }


    public List<GnomeEntity> convertGnomeListToGnomeEntityList(List<Gnome> gnomeList) {
        List<GnomeEntity> gnomeEntityList = new ArrayList<>();
        for (Gnome gnome : gnomeList) {
            GnomeEntity gnomeEntity = new GnomeEntity(gnome.name, gnome.thumbnail, gnome.age, gnome.weight
                    , gnome.height, gnome.hairColor, gnome.professions, gnome.friends, gnome.id);//
            gnomeEntityList.add(gnomeEntity);
        }

        return gnomeEntityList;
    }

    public List<Gnome> convertGnomeEntityListToGnomeList(List<GnomeEntity> gnomeEntityList) {
        List<Gnome> gnomeList = new ArrayList<>();
        for (GnomeEntity gnomeEntity : gnomeEntityList) {
            Gnome gnome = new Gnome(gnomeEntity.getIdRemote().intValue(), gnomeEntity.getGnomeName(), gnomeEntity.getThumbnail(), gnomeEntity.getAge(),
                    gnomeEntity.getWeight(), gnomeEntity.getHeight(), gnomeEntity.getHairColor(), gnomeEntity.getProfessions(), gnomeEntity.getFriends());
            gnomeList.add(gnome);
        }

        return gnomeList;
    }

    //db related
    public void insertToDb(final List<GnomeEntity> gnomeEntityList) {

        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mGnomeDAO.insertGnomes(gnomeEntityList);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.e(LOG_TAG, "SUCCESFULLY INSERTED");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(LOG_TAG, e.getMessage());
                    }
                }));

    }

    public void deleteAllRows() {

        compositeDisposable.add(Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        mGnomeDAO.clearGnomesTable();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.e(LOG_TAG, "SUCCESFULLY ERASED");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(LOG_TAG, e.getMessage());

                            }
                        })
        );

    }

    public void clearDbStreams(){
        compositeDisposable.clear();
    }
}
