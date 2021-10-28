package com.kirabium.catfactgenerator.ui.catfacts;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class CatFactsViewState {

    @NonNull
    private final List<String> catFacts;

    private final boolean isPreviousPageButtonEnabled;

    public CatFactsViewState(
        @NonNull List<String> catFacts,
        boolean isPreviousPageButtonEnabled
    ) {
        this.catFacts = catFacts;
        this.isPreviousPageButtonEnabled = isPreviousPageButtonEnabled;
    }

    @NonNull
    public List<String> getCatFacts() {
        return catFacts;
    }

    public boolean isPreviousPageButtonEnabled() {
        return isPreviousPageButtonEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatFactsViewState that = (CatFactsViewState) o;
        return isPreviousPageButtonEnabled == that.isPreviousPageButtonEnabled &&
            catFacts.equals(that.catFacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catFacts, isPreviousPageButtonEnabled);
    }

    @NonNull
    @Override
    public String toString() {
        return "CatFactsViewState{" +
            "catFacts=" + catFacts +
            ", isPreviousPageButtonEnabled=" + isPreviousPageButtonEnabled +
            '}';
    }
}
