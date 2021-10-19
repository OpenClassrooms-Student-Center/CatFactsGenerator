package com.kirabium.catfactgenerator.service;

import com.kirabium.catfactgenerator.model.CatFactsOutput;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("facts")
    Call<CatFactsOutput> getListOfCats(@Query("page") int page);
}