package com.example.ernest.axamobileassigment.screens.common;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.example.ernest.axamobileassigment.MyApplication;
import com.example.ernest.axamobileassigment.dependencyinjection.application.ApplicationComponent;
import com.example.ernest.axamobileassigment.dependencyinjection.presentation.PresentationComponent;
import com.example.ernest.axamobileassigment.dependencyinjection.presentation.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(this));

    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
