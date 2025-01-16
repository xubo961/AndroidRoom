package com.xubop961.palabrasroom;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PalabraListAdapter extends ListAdapter<Palabra, PalabraListAdapter.WordViewHolder> {

    private final Context context;
    private final PalabraViewModel mWordViewModel;

    public PalabraListAdapter(@NonNull DiffUtil.ItemCallback<Palabra> diffCallback, Context context, PalabraViewModel wordViewModel) {
        super(diffCallback);
        this.context = context;
        this.mWordViewModel = wordViewModel;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Palabra currentPalabra = getItem(position);
        holder.bind(currentPalabra.getPalabra());

        holder.botonEliminar.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Eliminar palabra")
                    .setMessage("¿Quieres Eliminar La Palabra Seleccionada?" + currentPalabra.getPalabra())
                    .setPositiveButton("Sí", (dialogInterface, i) -> {
                        mWordViewModel.delete(currentPalabra);
                        Toast.makeText(context, "Palabra borrada con éxito", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                    })
                    .show();
        });
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView tvPalabra;
        ImageView botonEliminar;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPalabra = itemView.findViewById(R.id.textView);
            botonEliminar = itemView.findViewById(R.id.Eliminar);
        }

        public void bind(String text) {
            tvPalabra.setText(text);
        }
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


