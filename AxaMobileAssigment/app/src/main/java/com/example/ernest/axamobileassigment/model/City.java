package com.example.ernest.axamobileassigment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @SerializedName("Brastlewark")
    @Expose
    public List<Gnome> brastlewark = null;

    public List<Gnome> getBrastlewark() {
        return brastlewark;
    }
}
