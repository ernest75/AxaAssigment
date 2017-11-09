package com.example.ernest.axamobileassigment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ernest.axamobileassigment.adapters.AxaAssigmentGnomesAdapter;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract;
import com.example.ernest.axamobileassigment.database.AxaAssigmentDbSqlite;
import com.example.ernest.axamobileassigment.model.RpgGameModel;
import com.example.ernest.axamobileassigment.volleyhelper.VolleySingleton;
import com.example.ernest.axamobileassigment.database.AxaAssigmentContract.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;


import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String UPLOAD_URL = "https://raw.githubusercontent.com/rrafols/mobile_test/master/data.json";
    private static final int LOADER_GNOMES_ID = 1;
    private static final int WRITE_EXTERNAL_STORAGE_ID = 2;


    ListView mLvGnomes;
    Button mBtnGetGnomes;
    Context mContext;
    ProgressBar mProgressBar;
    private AxaAssigmentGnomesAdapter mAdapter;

    private AxaAssigmentDbSqlite mOpenHelper;

    RpgGameModel mRpgGameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Uncomment that method when cheking sqlite db
        //requestPermission();

        mContext = this;
        mRpgGameModel = RpgGameModel.getInstance();

        //getting references
        mLvGnomes = (ListView)findViewById(R.id.lvGnomes);
        mBtnGetGnomes = (Button)findViewById(R.id.btnGetGnomes);
        mProgressBar = (ProgressBar)findViewById(R.id.pbWaitingCircle);

        //initializations
        mOpenHelper = new AxaAssigmentDbSqlite(mContext);
        mAdapter = new AxaAssigmentGnomesAdapter(mContext,null,0);

        mLvGnomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(mContext,GnomesDetail.class);
                int id_local = (int)id;
                intent.putExtra("id_local", id_local);
                startActivity(intent);
            }
        });
        mLvGnomes.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.GONE);

        mBtnGetGnomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                getGnomesFromServer();
            }
        });


        getSupportLoaderManager().initLoader(LOADER_GNOMES_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, AxaAssigmentContract.Gnomes.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);

    }

    public void getGnomesFromServer() {
        StringRequest stringRequest = new StringRequest(UPLOAD_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected Void doInBackground(String... strings) {

                            mRpgGameModel.InsertNewTown(strings[0], mContext);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }.execute(s);
                }
            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }


    boolean copyFile(String pathSrc, String pathDst){
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
    private void copyDeviceDatabase() {
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

    //need to handle permissions to be able to copy the db on external storage
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_ID);
//    }

}
