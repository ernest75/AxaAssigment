package com.example.ernest.axamobileassigment.screens.main;

import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.model.GnomeRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MainModel implements MainActivityMVP.Model {

    private GnomeRepository mGnomeRepository;

    public MainModel(GnomeRepository gnomeRepository) {
        this.mGnomeRepository = gnomeRepository;
    }

    @Override
    public Observable<List<Gnome>> getGnomesObservable() {
        return mGnomeRepository.getGnomesFromNetwork();
    }

    @Override
    public Flowable<List<Gnome>> getGnomesFromDb(){
        return mGnomeRepository.getGnomesFromDb();
    }

    @Override
    public void clearStreams() {
        mGnomeRepository.clearDbStreams();
    }

}
