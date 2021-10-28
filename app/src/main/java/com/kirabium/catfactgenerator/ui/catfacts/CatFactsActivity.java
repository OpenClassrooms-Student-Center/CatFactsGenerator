package com.kirabium.catfactgenerator.ui.catfacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kirabium.catfactgenerator.R;
import com.kirabium.catfactgenerator.ui.ViewModelFactory;

public class CatFactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cat_facts_activity);

        CatFactsViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(CatFactsViewModel.class);

        FloatingActionButton previousButton = findViewById(R.id.cat_prev);
        previousButton.setOnClickListener(v -> viewModel.onPreviousPageButtonClicked());

        FloatingActionButton nextButton = findViewById(R.id.cat_next);
        nextButton.setOnClickListener(v -> viewModel.onNextPageButtonClicked());

        RecyclerView recyclerView = findViewById(R.id.cat_rv);
        CatFactsAdapter adapter = new CatFactsAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getViewStateLiveData().observe(this, catFactsViewState -> {
            adapter.submitList(catFactsViewState.getCatFacts());
            previousButton.setEnabled(catFactsViewState.isPreviousPageButtonEnabled());
        });
    }
}