package com.example.ernest.axamobileassigment.screens.gnomedetail;

import com.example.ernest.axamobileassigment.model.GnomeApp;

public class GnomeDetailPresenter implements GnomeDetailMVP.Presenter {

    GnomeDetailMVP.View mView;

    GnomeDetailMVP.Model mModel;


    public GnomeDetailPresenter(GnomeDetailMVP.Model model) {
        mModel = model;
    }

    @Override
    public void setView(GnomeDetailMVP.View view) {
        mView = view;
    }

    @Override
    public GnomeApp getCurrentGnome() {
        return mModel.getGnomeToShow(mView.getIntentCreator());
    }


}
