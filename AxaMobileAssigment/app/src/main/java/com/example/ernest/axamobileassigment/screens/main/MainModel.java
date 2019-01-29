package com.example.ernest.axamobileassigment.screens.main;

import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.model.GnomeRepository;

import java.util.List;

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
}
