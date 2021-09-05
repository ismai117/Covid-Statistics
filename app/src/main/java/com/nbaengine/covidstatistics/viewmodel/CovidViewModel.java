package com.nbaengine.covidstatistics.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nbaengine.covidstatistics.model.CovidResponse;
import com.nbaengine.covidstatistics.network.ApiClient;
import com.nbaengine.covidstatistics.service.CovidService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidViewModel extends ViewModel {


    private String host = "covid-193.p.rapidapi.com";
    private String key = "0c42b61f32mshd336f80f0806bb5p150e69jsn83cc84b177b5";
    private MutableLiveData<CovidResponse> mutableLiveData;


    public MutableLiveData<CovidResponse> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<CovidResponse>();
        }

        return mutableLiveData;
    }


    public void MakeCovidStatCall(String country) {

        CovidService covidService = ApiClient.getRetrofit().create(CovidService.class);

        Call<CovidResponse> covidresponsecall = covidService.getCountryStat(host, key, country);

        covidresponsecall.enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                getMutableLiveData().postValue(response.body());
                Log.d("responseCall", "success");
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {
                getMutableLiveData().postValue(null);
                Log.d("responseCall", "failed: " + t.getMessage());
            }
        });


    }


}
