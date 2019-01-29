package com.example.ernest.axamobileassigment.networking;



import com.example.ernest.axamobileassigment.model.City;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GnomesApi {

    @GET("data.json")
    Call<City> getGnomes();

    @GET("data.json")
    Observable<City> getGnomesRx();

}
