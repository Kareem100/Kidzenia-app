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

public class ArabicNumbersActivity extends AppCompatActivity {

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
            mediaPlayer.setVolume(0, 0);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        final ArrayList<Data> arrayList = new ArrayList<Data>();
        arrayList.add(new Data(R.raw.sound_number_1_arabic, R.drawable.arabic_number_1, R.drawable.apple_1, "واحد"));
        arrayList.add(new Data(R.raw.sound_number_2_arabic, R.drawable.arabic_number_2, R.drawable.apples_2, "اثنان"));
        arrayList.add(new Data(R.raw.sound_number_3_arabic, R.drawable.arabic_number_3, R.drawable.apples_3, "ثلاثة"));
        arrayList.add(new Data(R.raw.sound_number_4_arabic, R.drawable.arabic_number_4, R.drawable.apples_4, "اربعة"));
        arrayList.add(new Data(R.raw.sound_number_5_arabic, R.drawable.arabic_number_5, R.drawable.apples_5, "خمسة"));
        arrayList.add(new Data(R.raw.sound_number_6_arabic, R.drawable.arabic_number_6, R.drawable.apples_6, "ستة"));
        arrayList.add(new Data(R.raw.sound_number_7_arabic, R.drawable.arabic_number_7, R.drawable.apples_7, "سبعة"));
        arrayList.add(new Data(R.raw.sound_number_8_arabic, R.drawable.arabic_number_8, R.drawable.apples_8, "ثمانية"));
        arrayList.add(new Data(R.raw.sound_number_9_arabic, R.drawable.arabic_number_9, R.drawable.apples_9, "تسعة"));
        arrayList.add(new Data(R.raw.sound_number_10_arabic, R.drawable.arabic_number_10, R.drawable.apples_10, "عشرة"));

        DataAdapter dataAdapter = new DataAdapter(this, arrayList);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMedia();
                mediaPlayer = mediaPlayer.create(ArabicNumbersActivity.this, arrayList.get(position).getRawID());
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