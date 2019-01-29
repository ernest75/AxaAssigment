package com.example.ernest.axamobileassigment.screens.gnomedetail;

import android.content.Intent;

import com.example.ernest.axamobileassigment.model.GnomeApp;

public interface GnomeDetailMVP {

    public interface View {

        Intent getIntentCreator();

        void showData();
    }

    public interface Presenter {

        void setView(GnomeDetailMVP.View view);

        GnomeApp getCurrentGnome();

    }

    public interface Model {

        GnomeApp getGnomeToShow(Intent intent);

    }


}
