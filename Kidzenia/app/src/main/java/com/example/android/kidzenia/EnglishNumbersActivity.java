package com.example.android.kidzenia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EnglishNumbersActivity extends AppCompatActivity
        implements DataAdapter.OnListItemClickListener {

    private ArrayList<Data> dataArrayList;
    private MediaPlayer mediaPlayer, music;
    private ImageView background;
    private boolean backPressed;
    protected static boolean VISIBLE_BACKGROUND = false, playMusic = false;
    private final MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
            if (background.getDrawable() != null) {
                background.animate().alpha(0).setDuration(1000);
                new Handler().postDelayed(() -> {
                    if (music != null) {
                        VISIBLE_BACKGROUND = false;
                        music.start();
                        playMusic = true;
                        background.setImageDrawable(null);
                    }
                }, 2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        music = MediaPlayer.create(this, R.raw.looped_music);
        music.setLooping(true);
        backPressed = false;

        background = findViewById(R.id.display_data_background);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        if (!VISIBLE_BACKGROUND) {
            background.animate().alpha(1).setDuration(1000);
            VISIBLE_BACKGROUND = true;
            playMusic = false;
            final int sound_id = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC)
                    ? R.raw.sound_english_numbers_in_arabic : R.raw.sound_english_numbers_in_english;
            mediaPlayer = MediaPlayer.create(this, sound_id);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        dataArrayList = new ArrayList<>();
        dataArrayList.add(new Data(R.raw.sound_1, R.drawable.number_1, R.drawable.apple_1, "One"));
        dataArrayList.add(new Data(R.raw.sound_2, R.drawable.number_2, R.drawable.apples_2, "Two"));
        dataArrayList.add(new Data(R.raw.sound_3, R.drawable.number_3, R.drawable.apples_3, "Three"));
        dataArrayList.add(new Data(R.raw.sound_4, R.drawable.number_4, R.drawable.apples_4, "Four"));
        dataArrayList.add(new Data(R.raw.sound_5, R.drawable.number_5, R.drawable.apples_5, "Five"));
        dataArrayList.add(new Data(R.raw.sound_6, R.drawable.number_6, R.drawable.apples_6, "Six"));
        dataArrayList.add(new Data(R.raw.sound_7, R.drawable.number_7, R.drawable.apples_7, "Seven"));
        dataArrayList.add(new Data(R.raw.sound_8, R.drawable.number_8, R.drawable.apples_8, "Eight"));
        dataArrayList.add(new Data(R.raw.sound_9, R.drawable.number_9, R.drawable.apples_9, "Nine"));
        dataArrayList.add(new Data(R.raw.sound_10, R.drawable.number_10, R.drawable.apples_10, "Ten"));

        DataAdapter dataAdapter = new DataAdapter(this, dataArrayList, this);
        recyclerView.setAdapter(dataAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        releaseMedia();
        mediaPlayer = MediaPlayer.create(EnglishNumbersActivity.this,
                dataArrayList.get(position).getRawID());
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(completionListener);
    }

    private void releaseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void releaseMusic() {
        music.release();
        music = null;
    }

    @Override
    public void onBackPressed() {
        finish();
        releaseMedia();
        releaseMusic();
        backPressed = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playMusic)
            music.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!backPressed)
            music.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!backPressed) {
            releaseMusic();
            releaseMedia();
        }
        playMusic = true;
    }
}