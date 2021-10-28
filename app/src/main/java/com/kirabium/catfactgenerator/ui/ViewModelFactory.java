package com.kirabium.catfactgenerator.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kirabium.catfactgenerator.data.CatFactsRepository;
import com.kirabium.catfactgenerator.data.RetrofitService;
import com.kirabium.catfactgenerator.ui.catfacts.CatFactsViewModel;

/**
 * The ViewModelFactory is the class responsible for the creation of every ViewModel in the application.
 * In this essence, its instance should be unique and available everywhere. This concept has a name :
 * this is the Singleton pattern (check the getInstance() method for more details).
 *
 * Since the ViewModelFactory is the "entry point" for injection, this class will also be responsible
 * of injecting correctly the dependencies, creating a "graph" or "tree" of injection
 *
 * In the end, the schema we could make about the injection is this :
 *
 * CatFactsActivity -->   CatFactsViewModel   --> CatFactsRepository --> CatFactsApi
 *       View       -->       ViewModel       -->     Repository     --> Datasource (here, a Retrofit Api)
 *                                ↑
 *                      Injection starts here,
 *                      in the ViewModel layer
 *
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory();
                }
            }
        }
        return factory;
    }

    // Here is our "graph / tree" of injection : CatFactsRepository needs CatApi, and later on, CatFactsViewModel will need CatFactsRepository
    private final CatFactsRepository catFactsRepository = new CatFactsRepository(
        // We inject the CatApi in the Repository constructor
        RetrofitService.getCatApi()
    );

    private ViewModelFactory() {
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CatFactsViewModel.class)) {
            // We inject the Repository in the ViewModel constructor
            return (T) new CatFactsViewModel(catFactsRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}