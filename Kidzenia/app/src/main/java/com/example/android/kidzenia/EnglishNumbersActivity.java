package com.example.android.kidzenia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnglishNumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer, music;
    private ImageView background;
    private ListView listView;
    private boolean backPressed;
    protected static boolean VISIBLE_BACKGROUND = false, playMusic = false;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
            if(background.getDrawable()!=null){
                background.animate().alpha(0).setDuration(1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
        listView = findViewById(R.id.list_view);

        if(!VISIBLE_BACKGROUND) {
            background.animate().alpha(1).setDuration(1000);
            VISIBLE_BACKGROUND = true;
            mediaPlayer = mediaPlayer.create(this, R.raw.sound_w);
            mediaPlayer.start();
            mediaPlayer.setVolume(0, 0);
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        final ArrayList<Data> arrayList = new ArrayList<Data>();
        arrayList.add(new Data(R.raw.sound_1, R.drawable.number_1, R.drawable.apple_1, "One"));
        arrayList.add(new Data(R.raw.sound_2, R.drawable.number_2, R.drawable.apples_2, "Two"));
        arrayList.add(new Data(R.raw.sound_3, R.drawable.number_3, R.drawable.apples_3, "Three"));
        arrayList.add(new Data(R.raw.sound_4, R.drawable.number_4, R.drawable.apples_4, "Four"));
        arrayList.add(new Data(R.raw.sound_5, R.drawable.number_5, R.drawable.apples_5, "Five"));
        arrayList.add(new Data(R.raw.sound_6, R.drawable.number_6, R.drawable.apples_6, "Six"));
        arrayList.add(new Data(R.raw.sound_7, R.drawable.number_7, R.drawable.apples_7, "Seven"));
        arrayList.add(new Data(R.raw.sound_8, R.drawable.number_8, R.drawable.apples_8, "Eight"));
        arrayList.add(new Data(R.raw.sound_9, R.drawable.number_9, R.drawable.apples_9, "Nine"));
        arrayList.add(new Data(R.raw.sound_10, R.drawable.number_10, R.drawable.apples_10, "Ten"));

        DataAdapter dataAdapter = new DataAdapter(this, arrayList);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMedia();
                mediaPlayer = mediaPlayer.create(EnglishNumbersActivity.this, arrayList.get(position).getRawID());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });
    }

    private void releaseMedia(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void releaseMusic(){
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
        if(playMusic)
            music.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!backPressed)
            music.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!backPressed){
            releaseMusic();
            releaseMedia();
        }
    }
}