package com.kirabium.catfactgenerator.ui.main;

import androidx.annotation.NonNull;

import java.util.Objects;

public class CatViewStateItem {

    @NonNull
    private final String fact;

    public CatViewStateItem(@NonNull String fact) {
        this.fact = fact;
    }
    @NonNull
    public String getFact() {
        return fact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatViewStateItem that = (CatViewStateItem) o;
        return fact.equals(that.fact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fact);
    }

    @NonNull
    @Override
    public String toString() {
        return "CatViewStateItem{" +
                ", fact='" + fact + '\'' +
                '}';
    }
}
