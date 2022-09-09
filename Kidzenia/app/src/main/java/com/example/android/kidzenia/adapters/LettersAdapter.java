package com.example.android.kidzenia.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.kidzenia.MainActivity;
import com.example.android.kidzenia.models.LetterModel;
import com.example.android.kidzenia.R;

import java.util.ArrayList;

public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.LetterViewHolder> {

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
    public LetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_letters_list_item,
                parent, false);

        return new LetterViewHolder(itemView, onListItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterViewHolder holder, int position) {
        LetterModel letterModelItem = letterModelArrayList.get(position);
        holder.bind(letterModelItem.getLetterNumberID(),
                letterModelItem.getAnimalAppleID(), letterModelItem.getName());
    }

    @Override
    public int getItemCount() {
        return letterModelArrayList.size();
    }

    public static class LetterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView letterOrNumberView;
        private final ImageView animalOrAppleView;
        private final TextView itemNameView;
        private final OnListItemClickListener onListItemClickListener;

        public LetterViewHolder(@NonNull View itemView,
                                OnListItemClickListener onListItemClickListener) {
            super(itemView);
            this.letterOrNumberView = itemView.findViewById(R.id.letters_numbers_gif);
            this.animalOrAppleView = itemView.findViewById(R.id.animals_apples_gif);
            this.itemNameView = itemView.findViewById(R.id.animals_apples_name);
            this.onListItemClickListener = onListItemClickListener;
            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        void bind(int letterNumberID, int animalAppleID, String itemNameTxt) {
            Glide.with(itemView.getContext()).asDrawable().load(letterNumberID).into(letterOrNumberView);
            Glide.with(itemView.getContext()).asDrawable().load(animalAppleID).into(animalOrAppleView);
            itemNameView.setText(itemNameTxt);

            if (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC)
                ((TextView)itemView.findViewById(R.id.pronounce_text)).setText("تـهـجـأ");
            else
                ((TextView)itemView.findViewById(R.id.pronounce_text)).setText("Pronounce");
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
