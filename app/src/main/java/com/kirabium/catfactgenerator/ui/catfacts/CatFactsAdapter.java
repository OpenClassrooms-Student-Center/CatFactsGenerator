package com.kirabium.catfactgenerator.ui.catfacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kirabium.catfactgenerator.R;

public class CatFactsAdapter extends ListAdapter<String, CatFactsAdapter.ViewHolder> {

    public CatFactsAdapter() {
        super(new ItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_facts_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView factTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            factTextView = itemView.findViewById(R.id.cat_item_fact);
        }

        public void bind(String catFact) {
            factTextView.setText(catFact);
        }
    }

    private static class ItemCallback extends DiffUtil.ItemCallback<String> {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return true;
        }
    }
}
