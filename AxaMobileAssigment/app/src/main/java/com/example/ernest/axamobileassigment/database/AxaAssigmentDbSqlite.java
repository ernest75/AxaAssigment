package com.example.ernest.axamobileassigment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract.*;

/**
 * Created by Ernest on 06/11/2017.
 */

public class AxaAssigmentDbSqlite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    static final String DATABASE_NAME = "axa_assigment.db";

    public AxaAssigmentDbSqlite(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_GNOMES_TABLE = "CREATE TABLE " + Gnomes.TABLE_NAME +
                " (" + Gnomes._ID + " INTEGER PRIMARY KEY, " + Gnomes.COLUMN_GNOME_NAME + " TEXT NOT NULL, " +
                Gnomes.COLUMN_URL_IMAGE + " TEXT , " + Gnomes.COLUMN_AGE + " INTEGER, " + Gnomes.COLUMN_REMOTE_ID +
                " INTEGER UNIQUE NOT NULL, " + Gnomes.COLUMN_WEIGHT + " REAL, " + Gnomes.COLUMN_HEIGHT + " REAL , " +
                Gnomes.COLUMN_HAIR_COLOR + " TEXT)";

        final String SQL_CREATE_PROFESIONS_TABLE = "CREATE TABLE " + Professions.TABLE_NAME + " (" +
                Professions._ID + " INTEGER PRIMARY KEY, " +
                Professions.COLUMN_PROFESION_NAME + " TEXT NOT NULL UNIQUE)";

        final String SQL_CREATE_GNOME_PROFESSIONS_TABLE = "CREATE TABLE " + GnomeProfessions.TABLE_NAME + " (" +
                GnomeProfessions._ID + " INTEGER PRIMARY KEY, " +
                GnomeProfessions.COLUMN_GNOME_LOCAL_ID + " INTEGER NOT NULL, " +
                GnomeProfessions.COLUMN_ID_PROFESSION + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + GnomeProfessions.COLUMN_GNOME_LOCAL_ID + ") REFERENCES " +
                Gnomes.TABLE_NAME + "(" + Gnomes._ID + ") ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (" + GnomeProfessions.COLUMN_ID_PROFESSION + ") REFERENCES " +
                Professions.TABLE_NAME + "(" + Professions._ID + ") ON DELETE CASCADE ON UPDATE CASCADE" +
                ")";

        final String SQL_CREATE_GNOME_FRIENDS_TABLE = "CREATE TABLE " + GnomeFriends.TABLE_NAME + " (" +
                GnomeFriends._ID + " INTEGER PRIMARY KEY, " +
                GnomeFriends.COLUMN_FRIEND_ID + " INTEGER NOT NULL, " +
                GnomeFriends.COLUMN_GNOME_LOCAL_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + GnomeFriends.COLUMN_GNOME_LOCAL_ID + ") REFERENCES " +
                Gnomes.TABLE_NAME + "(" + Gnomes._ID + ") ON DELETE CASCADE ON UPDATE CASCADE)";

        sqLiteDatabase.execSQL(SQL_CREATE_GNOMES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PROFESIONS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GNOME_FRIENDS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GNOME_PROFESSIONS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Gnomes.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GnomeFriends.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Professions.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GnomeProfessions.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
