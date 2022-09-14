package com.example.android.kidzenia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.annotation.SuppressLint;
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

    private VideoView videoView;
    private MediaPlayer mediaPlayer, music;
    private ArrayList<LanguageModel> languageModels;
    private Spinner spinner;
    private Button englishLettersBtn, arabicLettersBtn, englishNumbersBtn, arabicNumbersBtn, videosBtn;
    private boolean visibleSpinner;
    public static Boolean isSoundsOn = false;
    public static CurrentLocale currentLocale = CurrentLocale.ENGLISH;

    public enum CurrentLocale {
        ENGLISH, ARABIC
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.main_video);
        spinner = findViewById(R.id.main_spinner);
        englishLettersBtn = findViewById(R.id.main_english_letters_btn);
        englishNumbersBtn = findViewById(R.id.main_english_numbers_btn);
        arabicLettersBtn = findViewById(R.id.main_arabic_letters_btn);
        arabicNumbersBtn = findViewById(R.id.main_arabic_numbers_btn);
        videosBtn = findViewById(R.id.main_songs_btn);
        ImageView languageIcon = findViewById(R.id.main_lang_icon);
        ImageView soundsIcon = findViewById(R.id.main_sounds_icon);
        ImageView animalsAnimGif = findViewById(R.id.animals_anim_gif);
        ImageView busAnimGif = findViewById(R.id.bus_anim_gif);

        Glide.with(this).asGif().load(R.drawable.animals_anim).into(animalsAnimGif);
        Glide.with(this).asGif().load(R.drawable.bus_anim).into(busAnimGif);

        initSpinner();

        music = MediaPlayer.create(this, R.raw.looped_music);
        music.setLooping(true);
        music.start();

        handleMusicSound(soundsIcon);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.background_video);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(media -> {
            releaseVideo();
            mediaPlayer = media;
            mediaPlayer.setLooping(true);
        });

        languageIcon.setOnClickListener(view -> {
            if (!visibleSpinner) {
                spinner.setVisibility(View.VISIBLE);
                visibleSpinner = true;
            } else {
                spinner.setVisibility(View.GONE);
                visibleSpinner = false;
            }
        });

        soundsIcon.setOnClickListener(view -> handleMusicSound(soundsIcon));

        englishLettersBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "englishLetters")));
        englishNumbersBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "englishNumbers")));
        arabicLettersBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "arabicLetters")));
        arabicNumbersBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DisplayDataActivity.class).putExtra("dataType", "arabicNumbers")));
        videosBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, VideosActivity.class)));
        videosBtn.animate().scaleX(0.8f).setDuration(3000);
    }

    private void handleMusicSound(ImageView soundsIcon) {
        if (isSoundsOn) {
            music.setVolume(0f, 0f);
            soundsIcon.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_volume_off_24));
            isSoundsOn = false;
        } else {
            music.setVolume(1f, 1f);
            soundsIcon.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_volume_on_24));
            isSoundsOn = true;
        }
    }

    private void initSpinner() {
        languageModels = new ArrayList<>();
        languageModels.add(new LanguageModel(getString(R.string.english), R.drawable.england_flag));
        languageModels.add(new LanguageModel(getString(R.string.arabic), R.drawable.egypt_flag));

        LanguageAdapter adapter = new LanguageAdapter(this, languageModels);
        spinner.setAdapter(adapter);
        visibleSpinner = false;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String lang = ((LanguageModel) parent.getItemAtPosition(pos)).getLanguage();
                if (lang.equals("Arabic") || lang.equals("العربية")) {
                    englishLettersBtn.setText("الحروف الانجليزية");
                    englishNumbersBtn.setText("الارقام الانجليزية");
                    arabicLettersBtn.setText("الحروف العربية");
                    arabicNumbersBtn.setText("الارقام العربية");
                    videosBtn.setText("فــــــــديــــــــــــوهــــــــات تــعـلـيـمــيـة");
                    languageModels.get(0).setLanguage("الانجليزية");
                    languageModels.get(1).setLanguage("العربية");
                    currentLocale = CurrentLocale.ARABIC;
                } else {
                    englishLettersBtn.setText("ENGLISH LETTERS");
                    englishNumbersBtn.setText("ENGLISH NUMBERS");
                    arabicLettersBtn.setText("ARABIC LETTERS");
                    arabicNumbersBtn.setText("ARABIC NUMBERS");
                    videosBtn.setText("EDUCATIONAL VIDEOS");
                    languageModels.get(0).setLanguage("English");
                    languageModels.get(1).setLanguage("Arabic");
                    currentLocale = CurrentLocale.ENGLISH;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void releaseVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void releaseMusic() {
        if (music != null) {
            music.release();
            music = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        music.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // EnglishLettersActivity.VISIBLE_BACKGROUND=false;
        videoView.start();
        music.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseVideo();
        releaseMusic();
    }

}