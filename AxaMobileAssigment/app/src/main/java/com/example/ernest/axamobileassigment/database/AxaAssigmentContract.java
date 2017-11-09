package com.example.ernest.axamobileassigment.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ernest on 06/11/2017.
 */

public class AxaAssigmentContract {

    public static final String CONTENT_AUTHORITY = "com.example.ernest.axamobileassigment";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_GNOMES= "gnomes";
    public static final String PATH_GNOME_PROFESIONS = "gnomeProfesions";
    public static final String PATH_PROFESIONS = "profesions";
    public static final String PATH_GNOME_FRIENDS= "gnomeFriends";

    public static final class Gnomes implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GNOMES).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GNOMES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GNOMES;

        // Table Constants
        public static final String TABLE_NAME = "Gnomes";

        public static final String COLUMN_GNOME_NAME = "name";
        public static final String COLUMN_URL_IMAGE = "thumbnail";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_REMOTE_ID = "id_remote";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_HAIR_COLOR= "hair_color";

    }

    public static final class Professions implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROFESIONS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROFESIONS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROFESIONS;

        public static final String TABLE_NAME = "Professions";

        public static final String COLUMN_PROFESION_NAME = "profession_name";


    }

    public static final class GnomeFriends implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GNOME_FRIENDS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GNOME_FRIENDS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GNOME_FRIENDS;

        public static final String TABLE_NAME = "GnomeFriends";

        public static final String COLUMN_FRIEND_ID = "friend_id";
        public static final String COLUMN_GNOME_LOCAL_ID = "local_id";

    }

    public static final class GnomeProfessions implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GNOME_PROFESIONS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GNOME_PROFESIONS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GNOME_PROFESIONS;

        public static final String TABLE_NAME = "GnomeProfessions";

        public static final String COLUMN_ID_PROFESSION= "profession_id";
        public static final String COLUMN_GNOME_LOCAL_ID = "gnome_id";
    }
}
