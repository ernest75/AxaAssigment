package com.example.ernest.axamobileassigment.common;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public final class Constants {

    public static final String BASE_URL = "https://raw.githubusercontent.com/rrafols/mobile_test/master/";
    public static final String GNOME_NAME = "gnome_name";
    public static final String GNOME_AGE = "gnome_age";
    public static final String GNOME_WEIGHT = "gnome_weight";
    public static final String GNOME_HEIGHT = "gnome_height";
    public static final String GNOME_HAIR_COLOR = "gnome_hair_color";
    public static final String GNOME_PROFESSIONS = "gnome_professions";
    public static final String GNOME_FRIENDS = "gnome_friends";
    public static final String GNOME_PICTURE = "gnome_picture";

    public static boolean copyFile(String pathSrc, String pathDst){
        try {
            InputStream is = new FileInputStream(pathSrc);
            OutputStream os = new FileOutputStream(pathDst);

            byte[] buf = new byte[1024];
            int len;
            while((len = is.read(buf))>0){
                os.write(buf, 0, len);
            }
            is.close();
            os.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return true;
    }

    //method to extract the sqlite database from mobile to check it
    public static void copyDeviceDatabase() {
        File file = new File("/data/user/0/com.example.ernest.axamobileassigment/databases/axa_assigment.db");
        if(file.exists()){
            String dstPath = Environment.getExternalStorageDirectory() + "/db_backup.db";
            String srcPath =  "/data/user/0/com.example.ernest.axamobileassigment/databases/axa_assigment.db";//file.getAbsolutePath();
            copyFile(srcPath, dstPath);
        }
        else {
            Log.e("main", "Db Not Exists");
        }
    }



}
