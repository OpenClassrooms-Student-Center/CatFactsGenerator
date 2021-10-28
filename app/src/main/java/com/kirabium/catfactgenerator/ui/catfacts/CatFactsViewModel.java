package com.kirabium.catfactgenerator.ui.catfacts;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kirabium.catfactgenerator.model.CatFact;
import com.kirabium.catfactgenerator.data.CatFactsRepository;

import java.util.ArrayList;
import java.util.List;

public class CatFactsViewModel extends ViewModel {
    private final CatFactsRepository repository;

    private final MutableLiveData<Integer> currentPageMutableLiveData = new MutableLiveData<>();

    private final LiveData<CatFactsViewState> catFactsViewStateLiveData;

    public CatFactsViewModel(CatFactsRepository catFactsRepository) {
        repository = catFactsRepository;

        // We start the page at 0 (this will trigger the switchMap to query the first page from the server)
        currentPageMutableLiveData.setValue(0);

        // If the LiveData that contains the current page information changes...
        catFactsViewStateLiveData = Transformations.switchMap(currentPageMutableLiveData, currentPage ->
            // ... we query the repository to get the page (with a Transformations.switchMap)...
            Transformations.map(repository.getCatFactsLiveData(currentPage), catFacts ->
                // ... and we transform the data from the server to the ViewState (with a Transformations.map)
                mapDataToViewState(catFacts, currentPage)
            )
        );
    }

    // This is the "final product" of our ViewModel : every data needed from the view is in this LiveData
    public LiveData<CatFactsViewState> getViewStateLiveData() {
        return catFactsViewStateLiveData;
    }

    public void onPreviousPageButtonClicked() {
        Integer currentValue = currentPageMutableLiveData.getValue();
        if (currentValue == null || currentValue == 0) {
            return;
        }
        currentPageMutableLiveData.setValue(currentValue - 1);
    }

    public void onNextPageButtonClicked() {
        //noinspection ConstantConditions
        currentPageMutableLiveData.setValue(currentPageMutableLiveData.getValue() + 1);
    }

    private CatFactsViewState mapDataToViewState(@Nullable List<CatFact> catFacts, int currentPage) {
        List<String> catFactsToBeDisplayed = new ArrayList<>();

        if (catFacts != null) {
            // Mapping data from remote source to view data, ask to your mentor to know why it is important to do so
            for (CatFact cat : catFacts) {
                catFactsToBeDisplayed.add(cat.getFact());
            }
        }

        // Don't let user click to the previous button if the current page is 0 ;)
        boolean isPreviousPageButtonClickable = currentPage != 0;

        return new CatFactsViewState(
            catFactsToBeDisplayed,
            isPreviousPageButtonClickable
        );
    }
}