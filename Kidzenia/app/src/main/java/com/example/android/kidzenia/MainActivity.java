package com.example.android.kidzenia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView video;
    private MediaPlayer mediaPlayer;
    private Button englishLetters, arabicLetters, englishNumbers, arabicNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.main_video);
        englishLetters = findViewById(R.id.main_english_letters_btn);
        englishNumbers = findViewById(R.id.main_english_numbers_btn);
        arabicLetters = findViewById(R.id.main_arabic_letters_btn);
        arabicNumbers = findViewById(R.id.main_arabic_numbers_btn);

        Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.background_video);
        video.setVideoURI(uri);
        video.start();

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

            }
        });
        arabicNumbers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer media) {
                releaseMedia();
                mediaPlayer = media;
                mediaPlayer.setLooping(true);
            }
        });
    }

    private void releaseMedia(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        video.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EnglishLettersActivity.VISIBLE_BACKGROUND=false;
        video.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMedia();
    }
}