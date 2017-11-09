package com.example.ernest.axamobileassigment.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Looper;
import android.util.Log;

import com.example.ernest.axamobileassigment.database.AxaAssigmentContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by Ernest on 08/11/2017.
 */

public class RpgGameModel {

    public static Hashtable<String, Long> ProfessionsId = new Hashtable<String, Long>();
    public static Hashtable<String, Long> GnomesId = new Hashtable<String,Long>();

    private void RpgGameModel(){

    }

    private static RpgGameModel mInstance;

    public static RpgGameModel getInstance()  {
        if (mInstance == null)
        {
            mInstance = new RpgGameModel();
        }
        return mInstance;
    }

    public void InsertNewTown(String jsonString, Context context){

        int inserted = 0;
        Vector<ContentValues> vecProfessionsValues = new Vector<>();
        Vector<ContentValues> vecGnomesValues = new Vector<>();
        Vector<ContentValues> vecGnomesProfessions = new Vector<>();
        Vector<ContentValues> vecGnomesFriends = new Vector<>();

        //first insert professions
        parseProfessions(jsonString, vecProfessionsValues);
        ContentValues[] cvArrayProfessions = new ContentValues[vecProfessionsValues.size()];
        vecProfessionsValues.toArray(cvArrayProfessions);
        inserted = context.getContentResolver()
                .bulkInsert(AxaAssigmentContract.Professions.CONTENT_URI, cvArrayProfessions);

        //2nd insert gnomes
        parseGnomesJson(jsonString, vecGnomesValues);
        ContentValues[] cvArrayGnomes = new ContentValues[vecGnomesValues.size()];
        vecGnomesValues.toArray(cvArrayGnomes);
        inserted = context.getContentResolver()
                .bulkInsert(AxaAssigmentContract.Gnomes.CONTENT_URI, cvArrayGnomes);

        //3rd insert GnomeProfessions
        parseGnomesProfessions(jsonString, vecGnomesProfessions);
        ContentValues[] cvArrayGnomesProfessions = new ContentValues[vecGnomesProfessions.size()];
        vecGnomesProfessions.toArray(cvArrayGnomesProfessions);
        inserted = context.getContentResolver()
                .bulkInsert(AxaAssigmentContract.GnomeProfessions.CONTENT_URI, cvArrayGnomesProfessions);

        //4th insert GnomeFriends
        parseGnomesFriends(jsonString, vecGnomesFriends);
        ContentValues[] cvArrayGnomesFriends = new ContentValues[vecGnomesFriends.size()];
        vecGnomesFriends.toArray(cvArrayGnomesFriends);
        inserted = context.getContentResolver()
                .bulkInsert(AxaAssigmentContract.GnomeFriends.CONTENT_URI, cvArrayGnomesFriends);

    }

    public void parseProfessions(String jsonString, Vector<ContentValues> vecProfesionsValues)
    {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray gnomesArray = jsonObject.getJSONArray("Brastlewark");
            for (int i = 0 ; i < gnomesArray.length(); i++){
                JSONObject jo = gnomesArray.getJSONObject(i);
                JSONArray profesionsArray = jo.getJSONArray("professions");
                ContentValues values = new ContentValues();
                for(int j = 0 ; j < profesionsArray.length(); j++){
                    String profession = profesionsArray.getString(j);
                    values.put(AxaAssigmentContract.Professions.COLUMN_PROFESION_NAME, profession);
                    vecProfesionsValues.add(values);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void parseGnomesJson(String jsonString, Vector<ContentValues> vecGnomesValues){

        try {
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray gnomesArray = jsonObj.getJSONArray("Brastlewark");
            for (int i = 0; i < gnomesArray.length(); i++) {
                JSONObject jo = gnomesArray.getJSONObject(i);
                int idGnomeRemote = jo.getInt("id");
                int age = jo.getInt(AxaAssigmentContract.Gnomes.COLUMN_AGE);
                String name = jo.getString(AxaAssigmentContract.Gnomes.COLUMN_GNOME_NAME);
                String urlImage = jo.getString(AxaAssigmentContract.Gnomes.COLUMN_URL_IMAGE);
                float weight =(float)jo.getDouble(AxaAssigmentContract.Gnomes.COLUMN_WEIGHT);
                float height =(float)jo.getDouble(AxaAssigmentContract.Gnomes.COLUMN_HEIGHT);
                String hairColor = jo.getString(AxaAssigmentContract.Gnomes.COLUMN_HAIR_COLOR);
                ContentValues values = new ContentValues();
                values.put(AxaAssigmentContract.Gnomes.COLUMN_REMOTE_ID, idGnomeRemote);
                values.put(AxaAssigmentContract.Gnomes.COLUMN_AGE, age);
                values.put(AxaAssigmentContract.Gnomes.COLUMN_GNOME_NAME,name);
                values.put(AxaAssigmentContract.Gnomes.COLUMN_URL_IMAGE, urlImage);
                values.put(AxaAssigmentContract.Gnomes.COLUMN_WEIGHT, weight);
                values.put(AxaAssigmentContract.Gnomes.COLUMN_HEIGHT, height);
                values.put(AxaAssigmentContract.Gnomes.COLUMN_HAIR_COLOR, hairColor);

                vecGnomesValues.add(values);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void parseGnomesProfessions(String jsonString, Vector<ContentValues> vecGnomeProfessionsValues) {
        try {
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray gnomesArray = jsonObj.getJSONArray("Brastlewark");
            for (int i = 0; i < gnomesArray.length(); i++) {

                JSONObject jo = gnomesArray.getJSONObject(i);
                int idRemote = jo.getInt("id");
                int idLocal = GnomesId.get(jo.getString(AxaAssigmentContract.Gnomes.COLUMN_GNOME_NAME)).intValue();
                JSONArray profesionsArray = jo.getJSONArray("professions");
                for (int j = 0; j < profesionsArray.length(); j++) {
                    ContentValues values = new ContentValues();
                    values.put(AxaAssigmentContract.GnomeProfessions.COLUMN_GNOME_LOCAL_ID,idLocal);
                    int idProfesion = ProfessionsId.get(profesionsArray.getString(j)).intValue();
                    values.put(AxaAssigmentContract.GnomeProfessions.COLUMN_ID_PROFESSION,idProfesion);

                    vecGnomeProfessionsValues.add(values);
                }


            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void parseGnomesFriends(String jsonString, Vector<ContentValues> vecGnomeFriendsValues){
        try {
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray gnomesArray = jsonObj.getJSONArray("Brastlewark");
            for (int i = 0; i < gnomesArray.length(); i++) {

                JSONObject jo = gnomesArray.getJSONObject(i);
                int idRemote = jo.getInt("id");
                int idLocal = GnomesId.get(jo.getString(AxaAssigmentContract.Gnomes.COLUMN_GNOME_NAME)).intValue();
                JSONArray friendsArray = jo.getJSONArray("friends");
                for (int j = 0; j < friendsArray.length(); j++) {
                    ContentValues values = new ContentValues();
                    values.put(AxaAssigmentContract.GnomeFriends.COLUMN_GNOME_LOCAL_ID,idLocal);
                    int idFriend = GnomesId.get(friendsArray.getString(j)).intValue();
                    values.put(AxaAssigmentContract.GnomeFriends.COLUMN_FRIEND_ID,idFriend);
                    vecGnomeFriendsValues.add(values);
                }


            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public Cursor getGnomeProfessions(int id, Context context) {
        return context.getContentResolver().query(
                AxaAssigmentContract.GnomeProfessions.CONTENT_URI,
                new String[]{AxaAssigmentContract.Professions.COLUMN_PROFESION_NAME},
                AxaAssigmentContract.GnomeProfessions.TABLE_NAME + "." + AxaAssigmentContract.GnomeProfessions.COLUMN_GNOME_LOCAL_ID + "=?",
                new String[]{Integer.toString(id)},
                null
        );
    }

    public Cursor getGnomeInfo(int id, Context context) {
        return context.getContentResolver().query(
                AxaAssigmentContract.Gnomes.CONTENT_URI,
                new String[]{AxaAssigmentContract.Gnomes.COLUMN_AGE, AxaAssigmentContract.Gnomes.COLUMN_GNOME_NAME, AxaAssigmentContract.Gnomes.COLUMN_URL_IMAGE,
                        AxaAssigmentContract.Gnomes.COLUMN_HEIGHT, AxaAssigmentContract.Gnomes.COLUMN_WEIGHT, AxaAssigmentContract.Gnomes.COLUMN_HAIR_COLOR},
                AxaAssigmentContract.Gnomes._ID + "=?",
                new String[]{Integer.toString(id)},
                null,
                null
        );
    }

    public Cursor getGnomeFriends(int id, Context context) {
        return context.getContentResolver().query(
                AxaAssigmentContract.GnomeFriends.CONTENT_URI,
                new String[]{AxaAssigmentContract.Gnomes.COLUMN_GNOME_NAME},
                AxaAssigmentContract.GnomeFriends.TABLE_NAME + "." + AxaAssigmentContract.GnomeFriends.COLUMN_GNOME_LOCAL_ID+ "=?",
                new String[]{Integer.toString(id)},
                null
        );
    }
}
