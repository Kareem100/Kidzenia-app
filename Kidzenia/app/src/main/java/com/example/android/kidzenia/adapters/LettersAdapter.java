package com.example.android.kidzenia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.kidzenia.models.LetterModel;
import com.example.android.kidzenia.R;

import java.util.ArrayList;

public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.DataViewHolder> {

    private final Context context;
    private final ArrayList<LetterModel> letterModelArrayList;
    private final OnListItemClickListener onListItemClickListener;

    public LettersAdapter(Context context, ArrayList<LetterModel> letterModelArrayList,
                          OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.letterModelArrayList = letterModelArrayList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.letters_numbers_list_item,
                parent, false);

        return new DataViewHolder(itemView, onListItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        LetterModel letterModelItem = letterModelArrayList.get(position);
        holder.bind(context, letterModelItem.getLetterNumberID(),
                letterModelItem.getAnimalAppleID(), letterModelItem.getName());
    }

    @Override
    public int getItemCount() {
        return letterModelArrayList.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView letterOrNumberView;
        private final ImageView animalOrAppleView;
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

        void bind(Context context, int letterNumberID, int animalAppleID, String itemNameTxt) {
            Glide.with(context).asDrawable().load(letterNumberID).into(letterOrNumberView);
            Glide.with(context).asDrawable().load(animalAppleID).into(animalOrAppleView);
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
