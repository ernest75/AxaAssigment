package com.example.ernest.axamobileassigment.model;

import java.util.ArrayList;
import java.util.List;

public class GnomeRepo {


    public GnomeRepo(Integer id, String name, Integer age, Double weight, Double height,
                     String hairColor, ArrayList<String> professions, ArrayList<String> friends, String urlImage) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        this.professions = professions;
        this.friends = friends;
        this.urlImage = urlImage;
    }
    public Integer id;
    public String name;
    public Integer age;
    public Double weight;
    public Double height;
    public String hairColor;
    public ArrayList<String> professions = null;
    public ArrayList<String> friends = null;
    public String urlImage;
}

