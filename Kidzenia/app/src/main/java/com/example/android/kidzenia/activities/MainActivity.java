package com.example.android.kidzenia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.android.kidzenia.R;
import com.example.android.kidzenia.adapters.LanguageAdapter;
import com.example.android.kidzenia.models.LanguageModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private VideoView video;
    private MediaPlayer mediaPlayer, music;
    private ArrayList<LanguageModel> languageModels;
    private Spinner spinner;
    private Button englishLetters, arabicLetters, englishNumbers, arabicNumbers, songs;
    private boolean visibleSpinner;
    public static CurrentLocale currentLocale = CurrentLocale.ENGLISH;

    public enum CurrentLocale {
        ENGLISH, ARABIC
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.main_video);
        spinner = findViewById(R.id.main_spinner);
        englishLetters = findViewById(R.id.main_english_letters_btn);
        englishNumbers = findViewById(R.id.main_english_numbers_btn);
        arabicLetters = findViewById(R.id.main_arabic_letters_btn);
        arabicNumbers = findViewById(R.id.main_arabic_numbers_btn);
        songs = findViewById(R.id.main_songs_btn);
        ImageView languageIcon = findViewById(R.id.main_lang_icon);
        ImageView animalsAnimGif = findViewById(R.id.animals_anim_gif);
        ImageView busAnimGif = findViewById(R.id.bus_anim_gif);

        Glide.with(this).asGif().load(R.drawable.animals_anim).into(animalsAnimGif);
        Glide.with(this).asGif().load(R.drawable.bus_anim).into(busAnimGif);

        initSpinner();

        music = MediaPlayer.create(this, R.raw.looped_music);
        music.setLooping(true);
        music.start();

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.background_video);
        video.setVideoURI(uri);
        video.start();
        songs.animate().scaleX(0.8f).setDuration(3000);

        languageIcon.setOnClickListener(view -> {
            if(!visibleSpinner){
                spinner.setVisibility(View.VISIBLE);
                visibleSpinner = true;
            }

            else {
                spinner.setVisibility(View.GONE);
                visibleSpinner = false;
            }
        });
        englishLetters.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "englishLetters")));
        englishNumbers.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "englishNumbers")));
        arabicLetters.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "arabicLetters")));
        arabicNumbers.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "arabicNumbers")));
        songs.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, VideosActivity.class)));

        video.setOnPreparedListener(media -> {
            releaseVideo();
            mediaPlayer = media;
            mediaPlayer.setLooping(true);
        });
    }

    private void initSpinner(){
        languageModels = new ArrayList<>();
        languageModels.add(new LanguageModel(getString(R.string.english), R.drawable.england_flag));
        languageModels.add(new LanguageModel(getString(R.string.arabic), R.drawable.egypt_flag));

        LanguageAdapter adapter = new LanguageAdapter(this, languageModels);
        spinner.setAdapter(adapter);
        visibleSpinner = false;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String lang = ((LanguageModel) parent.getItemAtPosition(pos)).getLanguage();
                if (lang.equals("Arabic") || lang.equals("العربية")){
                    englishLetters.setText("الحروف الانجليزية");
                    englishNumbers.setText("الارقام الانجليزية");
                    arabicLetters.setText("الحروف العربية");
                    arabicNumbers.setText("الارقام العربية");
                    songs.setText("فديوهات تعليمية");
                    languageModels.get(0).setLanguage("الانجليزية");
                    languageModels.get(1).setLanguage("العربية");
                    currentLocale = CurrentLocale.ARABIC;
                }
                else {
                    englishLetters.setText(getString(R.string.english_letters));
                    englishNumbers.setText(getString(R.string.english_numbers));
                    arabicLetters.setText(getString(R.string.arabic_letters));
                    arabicNumbers.setText(getString(R.string.arabic_numbers));
                    songs.setText(getString(R.string.educational_videos));
                    languageModels.get(0).setLanguage(getString(R.string.english));
                    languageModels.get(1).setLanguage(getString(R.string.arabic));
                    currentLocale = CurrentLocale.ENGLISH;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    private void releaseVideo(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void releaseMusic(){
        if (music != null) {
            music.release();
            music = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        video.pause();
        music.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // EnglishLettersActivity.VISIBLE_BACKGROUND=false;
        video.start();
        music.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseVideo();
        releaseMusic();
    }

}