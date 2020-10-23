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

public class ArabicLettersActivity extends AppCompatActivity {

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
        arrayList.add(new Data(R.raw.sound_arabic_1, R.drawable.arabic_alphabet_1, R.drawable.arabic_animal_rabbit, "أرنب"));
        arrayList.add(new Data(R.raw.sound_arabic_2, R.drawable.arabic_alphabet_2, R.drawable.arabic_animal_duck, "بطة"));
        arrayList.add(new Data(R.raw.sound_arabic_3, R.drawable.arabic_alphabet_3, R.drawable.arabic_animal_crocodile, "تمساح"));
        arrayList.add(new Data(R.raw.sound_arabic_4, R.drawable.arabic_alphabet_4, R.drawable.arabic_animal_fox, "ثعلب"));
        arrayList.add(new Data(R.raw.sound_arabic_5, R.drawable.arabic_alphabet_5, R.drawable.arabic_animal_camel, "جمل"));
        arrayList.add(new Data(R.raw.sound_arabic_6, R.drawable.arabic_alphabet_6, R.drawable.arabic_animal_horse, "حصان"));
        arrayList.add(new Data(R.raw.sound_arabic_7, R.drawable.arabic_alphabet_7, R.drawable.arabic_animal_pig, "خنزير"));
        arrayList.add(new Data(R.raw.sound_arabic_8, R.drawable.arabic_alphabet_8, R.drawable.arabic_animal_hen, "ديك"));
        arrayList.add(new Data(R.raw.sound_arabic_9, R.drawable.arabic_alphabet_9, R.drawable.arabic_animal_wolf, "ذئب"));
        arrayList.add(new Data(R.raw.sound_arabic_10, R.drawable.arabic_alphabet_10, R.drawable.arabic_animal_racoon, "راكون"));
        arrayList.add(new Data(R.raw.sound_arabic_11, R.drawable.arabic_alphabet_11, R.drawable.arabic_animal_giraf, "زرافة"));
        arrayList.add(new Data(R.raw.sound_arabic_12, R.drawable.arabic_alphabet_12, R.drawable.arabic_animal_fish, "سمكة"));
        arrayList.add(new Data(R.raw.sound_arabic_13, R.drawable.arabic_alphabet_13, R.drawable.arabic_animal_monkey, "شمبانزى"));
        arrayList.add(new Data(R.raw.sound_arabic_14, R.drawable.arabic_alphabet_14, R.drawable.arabic_animal_falcon, "صقر"));
        arrayList.add(new Data(R.raw.sound_arabic_15, R.drawable.arabic_alphabet_15, R.drawable.arabic_animal_frog, "ضفدع"));
        arrayList.add(new Data(R.raw.sound_arabic_16, R.drawable.arabic_alphabet_16, R.drawable.arabic_animal_peacock, "طاووس"));
        arrayList.add(new Data(R.raw.sound_arabic_17, R.drawable.arabic_alphabet_17, R.drawable.arabic_animal_antelope, "ظبى"));
        arrayList.add(new Data(R.raw.sound_arabic_18, R.drawable.arabic_alphabet_18, R.drawable.arabic_animal_bird, "عصفور"));
        arrayList.add(new Data(R.raw.sound_arabic_19, R.drawable.arabic_alphabet_19, R.drawable.arabic_animal_crow, "غراب"));
        arrayList.add(new Data(R.raw.sound_arabic_20, R.drawable.arabic_alphabet_20, R.drawable.arabic_animal_elephant, "فيل"));
        arrayList.add(new Data(R.raw.sound_arabic_21, R.drawable.arabic_alphabet_21, R.drawable.arabic_animal_cat, "قطة"));
        arrayList.add(new Data(R.raw.sound_arabic_22, R.drawable.arabic_alphabet_22, R.drawable.arabic_animal_dog, "كلب"));
        arrayList.add(new Data(R.raw.sound_arabic_23, R.drawable.arabic_alphabet_23, R.drawable.arabic_animal_lama, "لاما"));
        arrayList.add(new Data(R.raw.sound_arabic_24, R.drawable.arabic_alphabet_24, R.drawable.arabic_animal_sheep, "ماعز"));
        arrayList.add(new Data(R.raw.sound_arabic_25, R.drawable.arabic_alphabet_25, R.drawable.arabic_animal_tiger, "نمر"));
        arrayList.add(new Data(R.raw.sound_arabic_26, R.drawable.arabic_alphabet_26, R.drawable.arabic_animal_hoopoe, "هدهد"));
        arrayList.add(new Data(R.raw.sound_arabic_27, R.drawable.arabic_alphabet_27, R.drawable.arabic_animal_rhino, "وحيد القرن"));
        arrayList.add(new Data(R.raw.sound_arabic_28, R.drawable.arabic_alphabet_28, R.drawable.arabic_animal_dragonfly, "يعسوب"));

        DataAdapter dataAdapter = new DataAdapter(this, arrayList);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMedia();
                mediaPlayer = mediaPlayer.create(ArabicLettersActivity.this, arrayList.get(position).getRawID());
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