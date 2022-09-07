package com.example.android.kidzenia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private final Context context;
    private final ArrayList<Data> dataArrayList;
    private final OnListItemClickListener onListItemClickListener;

    public DataAdapter(Context context, ArrayList<Data> dataArrayList,
                       OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item,
                parent, false);

        return new DataViewHolder(itemView, onListItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data dataItem = dataArrayList.get(position);
        holder.bind(dataItem.getLetterNumberID(), dataItem.getAnimalAppleID(), dataItem.getName());
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final GifImageView letterOrNumberView;
        private final GifImageView animalOrAppleView;
        private final TextView itemNameView;
        private final OnListItemClickListener onListItemClickListener;

        public DataViewHolder(@NonNull View itemView,
                              OnListItemClickListener onListItemClickListener) {
            super(itemView);
            this.letterOrNumberView = itemView.findViewById(R.id.letters_numbers_gif);
            this.animalOrAppleView = itemView.findViewById(R.id.animals_apples_gif);
            this.itemNameView = itemView.findViewById(R.id.animals_apples_name);
            this.onListItemClickListener = onListItemClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(int letterNumberID, int animalAppleID, String itemNameTxt) {
            letterOrNumberView.setImageResource(letterNumberID);
            animalOrAppleView.setImageResource(animalAppleID);
            itemNameView.setText(itemNameTxt);
        }

        @Override
        public void onClick(View view) {
            onListItemClickListener.onItemClickListener(getBindingAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onItemClickListener(int position);
    }
}
