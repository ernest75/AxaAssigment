package com.example.ernest.axamobileassigment.dependencyinjection.presentation;



import com.example.ernest.axamobileassigment.GnomesDetail;
import com.example.ernest.axamobileassigment.MainActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(MainActivity mainActivity);

    void inject(GnomesDetail gnomesDetail);
}
