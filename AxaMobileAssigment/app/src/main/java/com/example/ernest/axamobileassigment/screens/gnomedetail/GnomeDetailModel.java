package com.example.ernest.axamobileassigment.screens.gnomedetail;

import android.content.Intent;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.model.GnomeRepo;

public class GnomeDetailModel implements GnomeDetailMVP.Model  {

    @Override
    public GnomeRepo getGnomeToShow(Intent intent) {
            return new GnomeRepo(intent.getIntExtra(Constants.GNOME_ID,-1),
                    intent.getStringExtra(Constants.GNOME_NAME),
                    intent.getIntExtra(Constants.GNOME_AGE,-1),
                    intent.getDoubleExtra(Constants.GNOME_WEIGHT,-1),
                    intent.getDoubleExtra(Constants.GNOME_HEIGHT,-1),
                    intent.getStringExtra(Constants.GNOME_HAIR_COLOR),
                    intent.getStringArrayListExtra(Constants.GNOME_PROFESSIONS),
                    intent.getStringArrayListExtra(Constants.GNOME_FRIENDS),
                    intent.getStringExtra(Constants.GNOME_PICTURE));
        }
    }

