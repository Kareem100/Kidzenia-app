package com.example.android.kidzenia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private VideoView video;
    private MediaPlayer mediaPlayer, music;
    private ArrayList<LanguageItem> languageItems;
    private LanguageAdapter adapter;
    private ImageView languageIcon;
    private Spinner spinner;
    private Button englishLetters, arabicLetters, englishNumbers, arabicNumbers, songs;
    private boolean visibleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.main_video);
        languageIcon = findViewById(R.id.main_lang_icon);
        spinner = findViewById(R.id.main_spinner);
        englishLetters = findViewById(R.id.main_english_letters_btn);
        englishNumbers = findViewById(R.id.main_english_numbers_btn);
        arabicLetters = findViewById(R.id.main_arabic_letters_btn);
        arabicNumbers = findViewById(R.id.main_arabic_numbers_btn);
        songs = findViewById(R.id.main_songs_btn);

        initSpinner();

        music = music.create(this, R.raw.looped_music);
        music.setLooping(true);
        music.start();

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.background_video);
        video.setVideoURI(uri);
        video.start();
        songs.animate().scaleX(0.8f).setDuration(3000);

        languageIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!visibleSpinner){
                    spinner.setVisibility(View.VISIBLE);
                    visibleSpinner = true;
                }

                else {
                    spinner.setVisibility(View.GONE);
                    visibleSpinner = false;
                }
            }
        });
        englishLetters.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EnglishLettersActivity.class));
            }
        });
        englishNumbers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EnglishNumbersActivity.class));
            }
        });
        arabicLetters.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArabicLettersActivity.class));
            }
        });
        arabicNumbers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArabicNumbersActivity.class));
            }
        });
        songs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VideosActivity.class));
            }
        });

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer media) {
                releaseVideo();
                mediaPlayer = media;
                mediaPlayer.setLooping(true);
            }
        });
    }

    private void initSpinner(){
        languageItems = new ArrayList<LanguageItem>();
        languageItems.add(new LanguageItem(getString(R.string.english), R.drawable.england_flag));
        languageItems.add(new LanguageItem(getString(R.string.arabic), R.drawable.egypt_flag));

        adapter = new LanguageAdapter(this, languageItems);
        spinner.setAdapter(adapter);
        visibleSpinner = false;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String lang = ((LanguageItem) parent.getItemAtPosition(pos)).getLanguage();
                if (lang.equals("Arabic") || lang.equals("العربية")){
                    englishLetters.setText("الحروف الانجليزية");
                    englishNumbers.setText("الارقام الانجليزية");
                    arabicLetters.setText("الحروف العربية");
                    arabicNumbers.setText("الارقام العربية");
                    songs.setText("فديوهات");
                    languageItems.get(0).setLanguage("الانجليزية");
                    languageItems.get(1).setLanguage("العربية");
                }
                else {
                    englishLetters.setText("English Letters");
                    englishNumbers.setText("English Numbers");
                    arabicLetters.setText("Arabic Letters");
                    arabicNumbers.setText("Arabic Numbers");
                    songs.setText("Music Videos");
                    languageItems.get(0).setLanguage("English");
                    languageItems.get(1).setLanguage("Arabic");
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