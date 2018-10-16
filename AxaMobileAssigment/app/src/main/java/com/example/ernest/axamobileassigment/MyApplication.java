package com.example.ernest.axamobileassigment;

import android.app.Application;

import com.example.ernest.axamobileassigment.dependencyinjection.application.ApplicationComponent;
import com.example.ernest.axamobileassigment.dependencyinjection.application.ApplicationModule;
import com.example.ernest.axamobileassigment.dependencyinjection.application.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
