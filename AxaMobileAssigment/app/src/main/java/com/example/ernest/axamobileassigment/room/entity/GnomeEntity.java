package com.example.ernest.axamobileassigment.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.room.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = Constants.TABLE_NAME_GNOMES)
@TypeConverters(Converters.class)
public class GnomeEntity {

    public GnomeEntity(String gnomeName, String thumbnail, Integer age, Double weight,
                       Double height, String hairColor, List<String> professions, List<String> friends, long idRemote) {
        this.gnomeName = gnomeName;
        this.thumbnail = thumbnail;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
//        this.professions.addAll(professions);
//        this.friends.addAll(friends);
        this.professions = (ArrayList<String>) professions;
        this.friends = (ArrayList<String>) friends;
        this.idRemote = idRemote;
    }


    public GnomeEntity(){

    }

    @ColumnInfo(name=Constants.GNOME_ID)
    @PrimaryKey(autoGenerate =true)
    private long id;

    @ColumnInfo(name=Constants.ROW_GNOME_NAME)
    private String gnomeName;

    @ColumnInfo(name=Constants.ROW_GNOME_THUMBNAIL)
    private String thumbnail;

    @ColumnInfo(name=Constants.ROW_GNOME_AGE)
    private Integer age;

    @ColumnInfo(name=Constants.ROW_GNOME_WEIGHT)
    private Double weight;

    @ColumnInfo(name=Constants.ROW_GNOME_HEIGHT)
    private Double height;

    @ColumnInfo(name=Constants.ROW_GNOME_HAIR_COLOR)
    private String hairColor;

    @ColumnInfo(name=Constants.ROW_GNOME_PROFESSIONS_LIST)
    private ArrayList<String> professions = null;

    @ColumnInfo(name=Constants.ROW_GNOME_FRIENDS_LIST)
    private ArrayList<String> friends = null;

    @ColumnInfo(name=Constants.ROW_GNOME_ID_REMOTE)
    private Long idRemote;





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGnomeName() {
        return gnomeName;
    }

    public void setGnomeName(String gnomeName) {
        this.gnomeName = gnomeName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public ArrayList<String> getProfessions() {
        return professions;
    }

    public void setProfessions(ArrayList<String> professions) {
        this.professions = professions;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }


    public Long getIdRemote() {
        return idRemote;
    }

    public void setIdRemote(Long idRemote) {
        this.idRemote = idRemote;
    }
}
