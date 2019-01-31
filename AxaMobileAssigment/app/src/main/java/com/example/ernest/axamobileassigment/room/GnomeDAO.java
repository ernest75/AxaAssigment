package com.example.ernest.axamobileassigment.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.model.Gnome;
import com.example.ernest.axamobileassigment.room.entity.GnomeEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface GnomeDAO {

    @Insert
    void insertGnomes(List<GnomeEntity> gnomeEntityList);

    @Query("SELECT * FROM " + Constants.TABLE_NAME_GNOMES)
    Flowable<List<GnomeEntity>> getGnomes();

    @Query("DELETE FROM " + Constants.TABLE_NAME_GNOMES)
    public void clearGnomesTable();




}
