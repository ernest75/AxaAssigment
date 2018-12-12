package com.example.ernest.axamobileassigment.screens.gnomedetail;

import android.content.Intent;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.model.GnomeRepo;

import java.util.ArrayList;

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
    public GnomeRepo getCurrentGnome() {
        return mModel.getGnomeToShow(mView.getIntentCreator());
    }


}
