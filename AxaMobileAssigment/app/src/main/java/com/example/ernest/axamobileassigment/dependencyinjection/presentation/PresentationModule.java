package com.example.ernest.axamobileassigment.dependencyinjection.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.example.ernest.axamobileassigment.dependencyinjection.application.NetworkingModule;
import com.example.ernest.axamobileassigment.model.GnomeRepository;
import com.example.ernest.axamobileassigment.model.RpgGameModel;
import com.example.ernest.axamobileassigment.networking.GnomesApi;
import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomeDetailMVP;
import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomeDetailModel;
import com.example.ernest.axamobileassigment.screens.gnomedetail.GnomeDetailPresenter;
import com.example.ernest.axamobileassigment.screens.main.MainActivityPresenter;
import com.example.ernest.axamobileassigment.screens.main.MainModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final FragmentActivity mActivity;

    public PresentationModule(FragmentActivity fragmentActivity) {
        mActivity = fragmentActivity;
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    Context context(Activity activity) {
        return activity;
    }

    @Provides
    @Singleton
    RpgGameModel rpgGameModel(){
        return new RpgGameModel();
    }

    @Provides
    MainModel provideMainModel(GnomeRepository repository){
        return new MainModel(repository);
    }

    @Provides
    GnomeRepository provideGnomeRepository(GnomesApi gnomesApi){
        return new GnomeRepository(gnomesApi);
    }
    //todo continua per aqui, oju que estas passant com a argument la clase en si i no el interface definit a MainActivityMVP
    @Provides
    MainActivityPresenter provideMainPresenter(Context context, GnomesApi gnomesApi,MainModel mainModel ){
        return new MainActivityPresenter(context,gnomesApi,mainModel);
    }

    @Provides
    GnomeDetailMVP.Model provideGnomeDetailModel(){
        return new GnomeDetailModel();
    }

    @Provides
    GnomeDetailMVP.Presenter provideGnomeDetailPresenter(){
        return new GnomeDetailPresenter(provideGnomeDetailModel());
    }


//    @Provides
//    DialogsManager getDialogsManager(FragmentManager fragmentManager) {
//        return new DialogsManager(fragmentManager);
//    }

//    @Provides
//    ImageLoader getImageLoader(Activity activity) {
//        return new ImageLoader(activity);
//    }
}
