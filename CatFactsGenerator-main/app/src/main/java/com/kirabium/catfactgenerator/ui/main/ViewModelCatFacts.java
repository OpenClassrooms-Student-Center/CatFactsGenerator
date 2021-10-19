package com.kirabium.catfactgenerator.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kirabium.catfactgenerator.model.Datum;
import com.kirabium.catfactgenerator.service.CatRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewModelCatFacts extends ViewModel {
    private final CatRepository repository;


    public ViewModelCatFacts() {
        repository = CatRepository.getInstance();
    }

    //Mapping data from remote source to view data, ask to your mentor to know why it is important to do so
    private LiveData<List<CatViewStateItem>> mapDataToViewState(LiveData<List<Datum>> datums) {
        return Transformations.map(datums, cats -> {
            List<CatViewStateItem> catsViewStateItems = new ArrayList<>();
            for (Datum cat : cats) {
                catsViewStateItems.add(
                        new CatViewStateItem(
                                cat.getFact()
                        )
                );
            }
            return catsViewStateItems;
        });
    }

    public LiveData<List<CatViewStateItem>> getFirstPageCatFacts() {
        return mapDataToViewState(repository.getFirstPageListOfCatsOutputs());
    }

    public LiveData<List<CatViewStateItem>> getNextPageCatFacts() {
        return mapDataToViewState(repository.getNextPageListOfCatsOutputs());
    }

    public LiveData<List<CatViewStateItem>> getPreviousPageCatFacts() {
        return mapDataToViewState(repository.getPreviousPageListOfCatsOutputs());
    }
}