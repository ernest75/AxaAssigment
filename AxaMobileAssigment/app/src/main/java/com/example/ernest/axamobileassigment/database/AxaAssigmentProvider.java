package com.example.ernest.axamobileassigment.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ernest.axamobileassigment.MainActivity;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract.*;
import com.example.ernest.axamobileassigment.model.RpgGameModel;

import java.util.Locale;

/**
 * Created by Ernest on 06/11/2017.
 */

public class AxaAssigmentProvider extends ContentProvider {

    // DB helper
    private AxaAssigmentDbSqlite mOpenHelper;

    //ints to use on urimatcher compares
    static final int GNOMES = 100;
    static final int PROFESIONS= 200;
    static final int GNOME_FRIENDS = 300;
    static final int GNOME_PROFESSIONS = 400;
    static final int PROFESSIONS_GNOME_PROFESSIONS = 500;

    //sqlite query builders
    private static final SQLiteQueryBuilder sGnomesProfessionsWithProfessions;
    private static final SQLiteQueryBuilder sGnomesWithGnomeFriends;

    static
    {
        sGnomesProfessionsWithProfessions =  new SQLiteQueryBuilder();
        sGnomesWithGnomeFriends =  new SQLiteQueryBuilder();

        //Inner Join: Tables to ask for data
        sGnomesProfessionsWithProfessions.setTables(
                GnomeProfessions.TABLE_NAME + " INNER JOIN " +
                Professions.TABLE_NAME +
                " ON " + GnomeProfessions.TABLE_NAME +
                "." + GnomeProfessions.COLUMN_ID_PROFESSION +
                " = " + Professions.TABLE_NAME +
                "." + Professions._ID);

        sGnomesWithGnomeFriends.setTables(
                Gnomes.TABLE_NAME + " INNER JOIN " +
                        GnomeFriends.TABLE_NAME +
                        " ON " + Gnomes.TABLE_NAME +
                        "." + Gnomes._ID +
                        " = " + GnomeFriends.TABLE_NAME +
                        "." + GnomeFriends.COLUMN_FRIEND_ID);

    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new AxaAssigmentDbSqlite(getContext());
        return true;
    }

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static UriMatcher buildUriMatcher()
    {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        final String authority = AxaAssigmentContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, AxaAssigmentContract.PATH_GNOMES, GNOMES);
        matcher.addURI(authority, AxaAssigmentContract.PATH_PROFESIONS, PROFESIONS);
        matcher.addURI(authority, AxaAssigmentContract.PATH_GNOME_FRIENDS,GNOME_FRIENDS);
        matcher.addURI(authority, AxaAssigmentContract.PATH_GNOME_PROFESIONS,GNOME_PROFESSIONS);
        //matcher.addURI(authority, AxaAssigmentContract.PATH_PROFESSIONS_GNOME_PROFESSIONS,PROFESSIONS_GNOME_PROFESSIONS);
        return matcher;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {

            case PROFESIONS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        Professions.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case GNOMES:{
                retCursor = mOpenHelper.getReadableDatabase().query(
                        Gnomes.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }

            case GNOME_PROFESSIONS:{
                retCursor = sGnomesProfessionsWithProfessions.query(mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );
                break;
            }

            case GNOME_FRIENDS: {
                retCursor = sGnomesWithGnomeFriends.query(mOpenHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
       return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match)
        {
            case GNOMES:
                return Gnomes.CONTENT_TYPE;
            case PROFESIONS:
                return Professions.CONTENT_TYPE;
            case GNOME_FRIENDS:
                return GnomeFriends.CONTENT_TYPE;
            case GNOME_PROFESSIONS:
                return GnomeProfessions.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int returnCount = 0;

        if (Looper.myLooper() == Looper.getMainLooper())
            Log.e("BulkInsert", "In Main Thread");
        else
            Log.e("BulkInsert", "In IO Thread");

        switch (match) {

            case GNOMES: {
                // Erase all Rows
                db.delete(Gnomes.TABLE_NAME, null, null);
                RpgGameModel.GnomesId.clear();
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        String GnomeName = value.getAsString(Gnomes.COLUMN_GNOME_NAME);
                        long _id = db.insert(Gnomes.TABLE_NAME, null, value);
                        if (_id != -1) {
                            RpgGameModel.GnomesId.put(GnomeName, _id);
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    getContext().getContentResolver().notifyChange(Gnomes.CONTENT_URI, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case GNOME_FRIENDS: {
                // Erase all Rows
                db.delete(GnomeFriends.TABLE_NAME, null, null);
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(GnomeFriends.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    getContext().getContentResolver().notifyChange(GnomeFriends.CONTENT_URI, null);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case PROFESIONS: {
                // Erase all Rows
                db.delete(Professions.TABLE_NAME, null, null);
                RpgGameModel.ProfessionsId.clear();
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        String ProfName = value.getAsString(Professions.COLUMN_PROFESION_NAME);
                        Cursor idProf = db.query(Professions.TABLE_NAME, new String[]{Professions._ID},
                                              Professions.COLUMN_PROFESION_NAME + "=?",
                                              new String[]{ProfName},
                                              null,null,null);

                        if(idProf.getCount() <= 0) {
                            idProf.moveToFirst();
                            long _id = db.insert(Professions.TABLE_NAME, null, value);
                            RpgGameModel.ProfessionsId.put(ProfName, _id);
                            if (_id != -1) {
                                returnCount++;
                            }
                        }
                        idProf.close();
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    getContext().getContentResolver().notifyChange(Professions.CONTENT_URI, null);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case GNOME_PROFESSIONS: {
                // Erase all Rows
                db.delete(GnomeProfessions.TABLE_NAME, null, null);
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(GnomeProfessions.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    getContext().getContentResolver().notifyChange(GnomeProfessions.CONTENT_URI, null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

        }
        return returnCount;
    }
}
