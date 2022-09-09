package com.example.android.kidzenia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.kidzenia.models.LanguageModel;
import com.example.android.kidzenia.R;

import java.util.ArrayList;

public class LanguageAdapter extends ArrayAdapter<LanguageModel> {

    public LanguageAdapter(Context context, ArrayList<LanguageModel> languageModels) {
        super(context, 0, languageModels);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_spinner, parent, false);

        ImageView flag = convertView.findViewById(R.id.spinner_image_flag);
        TextView lang = convertView.findViewById(R.id.spinner_language);

        flag.setImageResource(getItem(position).getCountryFlag());
        lang.setText(getItem(position).getLanguage());

        return convertView;
    }

}
