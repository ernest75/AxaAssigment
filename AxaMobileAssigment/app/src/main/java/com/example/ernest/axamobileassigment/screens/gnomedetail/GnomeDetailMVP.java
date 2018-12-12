package com.example.ernest.axamobileassigment.screens.gnomedetail;

import android.content.Intent;

import com.example.ernest.axamobileassigment.model.GnomeRepo;

import java.util.ArrayList;

public interface GnomeDetailMVP {

    public interface View {
        Intent getIntentCreator();

        void showData();
    }

    public interface Presenter {

        void setView(GnomeDetailMVP.View view);

        GnomeRepo getCurrentGnome();

    }

    public interface Model {

        GnomeRepo getGnomeToShow(Intent intent);

    }


}
