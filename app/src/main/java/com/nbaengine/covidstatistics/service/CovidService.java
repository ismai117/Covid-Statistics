package com.nbaengine.covidstatistics.service;

import com.nbaengine.covidstatistics.model.CovidResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CovidService {

    @GET("statistics?")
    Call<CovidResponse> getCountryStat(
//            @Query("country") String country,
            @Header("x-rapidapi-host") String host,
            @Header("x-rapidapi-key") String key,
            @Query("country") String country
    );


}
