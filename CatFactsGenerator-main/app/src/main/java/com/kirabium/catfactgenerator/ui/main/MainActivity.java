package com.kirabium.catfactgenerator.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.kirabium.catfactgenerator.R;
import com.kirabium.catfactgenerator.ViewModelFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewModelCatFacts viewModel;
    private CatFactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initButton();
    }

    private void initButton() {
        findViewById(R.id.next).setOnClickListener(this);
        findViewById(R.id.prev).setOnClickListener(this);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.cat_rv);
        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(ViewModelCatFacts.class);
        adapter = new CatFactsAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getFirstPageCatFacts().observe(this, adapter::submitList);
    }

    private void nextPage(){
        viewModel.getNextPageCatFacts().observe(this, adapter::submitList);
    }
    private void prevPage(){
        viewModel.getPreviousPageCatFacts().observe(this, adapter::submitList);
    }

    @Override
    public void onClick(View view) {
        if(view==findViewById(R.id.prev)){
            prevPage();
        }else if(view==findViewById(R.id.next)){
            nextPage();
        }else{
            throw new IllegalArgumentException("View not found");
        }
    }
}