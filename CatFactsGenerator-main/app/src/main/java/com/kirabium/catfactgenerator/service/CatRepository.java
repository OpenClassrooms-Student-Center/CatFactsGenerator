package com.kirabium.catfactgenerator.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.kirabium.catfactgenerator.model.CatFactsOutput;
import com.kirabium.catfactgenerator.model.Datum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * I decided to apply the Singleton pattern to the repository, but you can also
 * inject it in your viewmodels from the ViewModel Factory, the most important
 * is to have a single instance of your repository in your app
 *
 */
public class CatRepository {
    private int page;
    private final ApiInterface myInterface;
    private final MutableLiveData<List<Datum>> listOfCats = new MutableLiveData<>();

    private static CatRepository catRepository;

    public static CatRepository getInstance() {
        if (catRepository == null) {
            catRepository = new CatRepository();
        }
        return catRepository;
    }

    public CatRepository() {
        myInterface = RetrofitService.getInterface();
    }

    /**
     * I developed the whole functionality of pagination until I realized that all pages are always random
     * in this API, and when you go back, it doesn't show the previous page you got ...
     *
     * At least, it can give you an example of such architectures, and if the API was correctly configured
     * the pagination would works
     */
    public MutableLiveData<List<Datum>> getFirstPageListOfCatsOutputs() {
        page = 1;
        return getListOfCatsOutputs(page);
    }

    public MutableLiveData<List<Datum>> getNextPageListOfCatsOutputs() {
        return getListOfCatsOutputs(++page);
    }

    public MutableLiveData<List<Datum>> getPreviousPageListOfCatsOutputs() {
        if (page == 1) return getFirstPageListOfCatsOutputs();
        return getListOfCatsOutputs(--page);
    }

    public MutableLiveData<List<Datum>> getListOfCatsOutputs(int page) {
        Call<CatFactsOutput> listOfCatOut = myInterface.getListOfCats(page);
        listOfCatOut.enqueue(new Callback<CatFactsOutput>() {
            @Override
            public void onResponse(@NonNull Call<CatFactsOutput> call, @NonNull Response<CatFactsOutput> response) {
                if (response.body() != null) {
                    listOfCats.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CatFactsOutput> call, @NonNull Throwable t) {
                listOfCats.postValue(null);
            }
        });
        return listOfCats;
    }
}