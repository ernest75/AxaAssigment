package com.example.ernest.axamobileassigment.room;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken< ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList( ArrayList<String> list) {
        Gson gson = new Gson();
        Type type = new TypeToken< ArrayList<String>>() {}.getType();
        String json = gson.toJson(list, type);
        return json;

    }
}