package com.xubop961.palabrasroom;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class PalabraListAdapter extends ListAdapter<Palabra, PalabraViewHolder> {

    public PalabraListAdapter(@NonNull DiffUtil.ItemCallback<Palabra> diffCallback) {
        super(diffCallback);
    }

    @Override
    public PalabraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PalabraViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(PalabraViewHolder holder, int position) {
        Palabra current = getItem(position);
        holder.bind(current.getPalabra());
    }

    static class PalabraDiff extends DiffUtil.ItemCallback<Palabra> {

        @Override
        public boolean areItemsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem) {
            return oldItem.getPalabra().equals(newItem.getPalabra());
        }
    }
}
