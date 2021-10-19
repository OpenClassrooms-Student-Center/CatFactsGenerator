package com.kirabium.catfactgenerator.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kirabium.catfactgenerator.R;


public class CatFactsAdapter extends ListAdapter<CatViewStateItem, CatFactsAdapter.ViewHolder> {


    public CatFactsAdapter() {
        super(new ListNeighbourItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cats_item, parent, false));
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

        public void bind(CatViewStateItem item) {
            factTextView.setText(item.getFact());
        }
    }

    private static class ListNeighbourItemCallback extends DiffUtil.ItemCallback<CatViewStateItem> {
        @Override
        public boolean areItemsTheSame(@NonNull CatViewStateItem oldItem, @NonNull CatViewStateItem newItem) {
            return oldItem.getFact().equals(newItem.getFact());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CatViewStateItem oldItem, @NonNull CatViewStateItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}
