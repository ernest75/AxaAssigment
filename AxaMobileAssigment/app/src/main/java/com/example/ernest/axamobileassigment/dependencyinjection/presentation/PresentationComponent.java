package com.example.ernest.axamobileassigment.dependencyinjection.presentation;



import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomesDetail;
import com.example.ernest.axamobileassigment.screens.gnomeslist.GnomesList;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(GnomesList gnomesList);

    void inject(GnomesDetail gnomesDetail);
}
