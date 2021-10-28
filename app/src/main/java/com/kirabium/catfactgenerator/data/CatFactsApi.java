package com.kirabium.catfactgenerator.data;

import com.kirabium.catfactgenerator.model.CatFactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatFactsApi {
    @GET("facts")
    Call<CatFactsResponse> getListOfCats(@Query("page") int page);
}