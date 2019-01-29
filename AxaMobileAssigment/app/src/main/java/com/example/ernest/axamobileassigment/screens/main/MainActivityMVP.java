package com.example.ernest.axamobileassigment.screens.main;

import com.example.ernest.axamobileassigment.model.Gnome;

import java.util.List;

import io.reactivex.Observable;

public interface MainActivityMVP {

    interface Model {

        Observable<List<Gnome>> getGnomesObservable();

        //void storeCall();
    }

    interface Presenter {

        void setView(MainActivityMVP.View view);

        void refreshButtonClicked();

        void onGnomeCellClicked(Gnome gnome);

        void clearStreams();


    }

    interface View {

        void showData(List<Gnome> gnomes);

        void showProgressbar();

        void hideProgressbar();

        void showErrorFromNetwork();

    }

}
