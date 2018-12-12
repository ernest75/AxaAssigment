package com.example.ernest.axamobileassigment.screens.main;

import com.example.ernest.axamobileassigment.model.Gnome;

import java.util.List;

public interface MainActivityMVP {

    interface Model {

        Gnome getGnomes();

        void storeCall();
    }

    interface Presenter {

        void setView(MainActivityMVP.View view);

        void refreshButtonClicked();

        void onGnomeCellClicked(Gnome gnome);

    }

    interface View {

        void showData(List<Gnome> gnomes);

        void showProgressbar();

        void hideProgressbar();

    }

}
