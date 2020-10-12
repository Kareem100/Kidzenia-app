package com.example.android.kidzenia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class DataAdapter extends ArrayAdapter<Data> {

    public DataAdapter(@NonNull Context context, ArrayList<Data> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView==null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        Data d = getItem(position);
        GifImageView letterOrNumber = listItemView.findViewById(R.id.letters_numbers_gif);
        GifImageView animalOrApple = listItemView.findViewById(R.id.animals_apples_gif);
        TextView name = listItemView.findViewById(R.id.animals_apples_name);

        letterOrNumber.setImageResource(d.getLetterNumberID());
        animalOrApple.setImageResource(d.getAnimalAppleID());
        name.setText(d.getName());

        return listItemView;
    }
}
