package com.example.ernest.axamobileassigment.dependencyinjection.presentation;



import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomesDetail;
import com.example.ernest.axamobileassigment.screens.main.MainActivity;
import com.example.ernest.axamobileassigment.PhotoDetailActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(MainActivity mainActivity);

    void inject(GnomesDetail gnomesDetail);

    void inject(PhotoDetailActivity photoDetailActivity);
}
