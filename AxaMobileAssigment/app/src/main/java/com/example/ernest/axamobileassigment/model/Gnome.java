package com.example.ernest.axamobileassigment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gnome {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("age")
    @Expose
    public Integer age;
    @SerializedName("weight")
    @Expose
    public Double weight;
    @SerializedName("height")
    @Expose
    public Double height;
    @SerializedName("hair_color")
    @Expose
    public String hairColor;
    @SerializedName("professions")
    @Expose
    public List<String> professions = null;
    @SerializedName("friends")
    @Expose
    public List<String> friends = null;
}
