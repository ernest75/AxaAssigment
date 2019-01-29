package com.example.ernest.axamobileassigment.dependencyinjection.application;



import com.example.ernest.axamobileassigment.constants.Constants;
import com.example.ernest.axamobileassigment.networking.GnomesApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
    }


    @Provides
    GnomesApi getGnomesApi(){
        return getRetrofit().create(GnomesApi.class);
    }



}
