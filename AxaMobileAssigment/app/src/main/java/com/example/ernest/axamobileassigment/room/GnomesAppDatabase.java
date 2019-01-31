package com.example.ernest.axamobileassigment.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.ernest.axamobileassigment.room.entity.GnomeEntity;

@Database(entities = {GnomeEntity.class},version = 1, exportSchema = false)
public abstract class GnomesAppDatabase extends RoomDatabase {

    public abstract GnomeDAO getGnomeDAO();

}
