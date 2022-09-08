package com.example.android.kidzenia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArabicLettersActivity extends AppCompatActivity
        implements DataAdapter.OnListItemClickListener {

    private ArrayList<Data> dataArrayList;
    private MediaPlayer mediaPlayer, music;
    private ImageView background;
    private boolean backPressed;
    protected static boolean VISIBLE_BACKGROUND = false, playMusic = false;
    private final MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.d("TAG", "onCompletion: REACHED !!!");
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
                    ? R.raw.sound_arabic_alphabet_in_arabic : R.raw.sound_arabic_alphabet_in_english;
            mediaPlayer = MediaPlayer.create(this, sound_id);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        dataArrayList = new ArrayList<>();
        dataArrayList.add(new Data(R.raw.sound_arabic_1, R.drawable.arabic_alphabet_1, R.drawable.arabic_animal_rabbit, "أرنب"));
        dataArrayList.add(new Data(R.raw.sound_arabic_2, R.drawable.arabic_alphabet_2, R.drawable.arabic_animal_duck, "بطة"));
        dataArrayList.add(new Data(R.raw.sound_arabic_3, R.drawable.arabic_alphabet_3, R.drawable.arabic_animal_crocodile, "تمساح"));
        dataArrayList.add(new Data(R.raw.sound_arabic_4, R.drawable.arabic_alphabet_4, R.drawable.arabic_animal_fox, "ثعلب"));
        dataArrayList.add(new Data(R.raw.sound_arabic_5, R.drawable.arabic_alphabet_5, R.drawable.arabic_animal_camel, "جمل"));
        dataArrayList.add(new Data(R.raw.sound_arabic_6, R.drawable.arabic_alphabet_6, R.drawable.arabic_animal_horse, "حصان"));
        dataArrayList.add(new Data(R.raw.sound_arabic_7, R.drawable.arabic_alphabet_7, R.drawable.arabic_animal_pig, "خنزير"));
        dataArrayList.add(new Data(R.raw.sound_arabic_8, R.drawable.arabic_alphabet_8, R.drawable.arabic_animal_hen, "ديك"));
        dataArrayList.add(new Data(R.raw.sound_arabic_9, R.drawable.arabic_alphabet_9, R.drawable.arabic_animal_wolf, "ذئب"));
        dataArrayList.add(new Data(R.raw.sound_arabic_10, R.drawable.arabic_alphabet_10, R.drawable.arabic_animal_racoon, "راكون"));
        dataArrayList.add(new Data(R.raw.sound_arabic_11, R.drawable.arabic_alphabet_11, R.drawable.arabic_animal_giraf, "زرافة"));
        dataArrayList.add(new Data(R.raw.sound_arabic_12, R.drawable.arabic_alphabet_12, R.drawable.arabic_animal_fish, "سمكة"));
        dataArrayList.add(new Data(R.raw.sound_arabic_13, R.drawable.arabic_alphabet_13, R.drawable.arabic_animal_monkey, "شمبانزى"));
        dataArrayList.add(new Data(R.raw.sound_arabic_14, R.drawable.arabic_alphabet_14, R.drawable.arabic_animal_falcon, "صقر"));
        dataArrayList.add(new Data(R.raw.sound_arabic_15, R.drawable.arabic_alphabet_15, R.drawable.arabic_animal_frog, "ضفدع"));
        dataArrayList.add(new Data(R.raw.sound_arabic_16, R.drawable.arabic_alphabet_16, R.drawable.arabic_animal_peacock, "طاووس"));
        dataArrayList.add(new Data(R.raw.sound_arabic_17, R.drawable.arabic_alphabet_17, R.drawable.arabic_animal_antelope, "ظبى"));
        dataArrayList.add(new Data(R.raw.sound_arabic_18, R.drawable.arabic_alphabet_18, R.drawable.arabic_animal_bird, "عصفور"));
        dataArrayList.add(new Data(R.raw.sound_arabic_19, R.drawable.arabic_alphabet_19, R.drawable.arabic_animal_crow, "غراب"));
        dataArrayList.add(new Data(R.raw.sound_arabic_20, R.drawable.arabic_alphabet_20, R.drawable.arabic_animal_elephant, "فيل"));
        dataArrayList.add(new Data(R.raw.sound_arabic_21, R.drawable.arabic_alphabet_21, R.drawable.arabic_animal_cat, "قطة"));
        dataArrayList.add(new Data(R.raw.sound_arabic_22, R.drawable.arabic_alphabet_22, R.drawable.arabic_animal_dog, "كلب"));
        dataArrayList.add(new Data(R.raw.sound_arabic_23, R.drawable.arabic_alphabet_23, R.drawable.arabic_animal_lama, "لاما"));
        dataArrayList.add(new Data(R.raw.sound_arabic_24, R.drawable.arabic_alphabet_24, R.drawable.arabic_animal_sheep, "ماعز"));
        dataArrayList.add(new Data(R.raw.sound_arabic_25, R.drawable.arabic_alphabet_25, R.drawable.arabic_animal_tiger, "نمر"));
        dataArrayList.add(new Data(R.raw.sound_arabic_26, R.drawable.arabic_alphabet_26, R.drawable.arabic_animal_hoopoe, "هدهد"));
        dataArrayList.add(new Data(R.raw.sound_arabic_27, R.drawable.arabic_alphabet_27, R.drawable.arabic_animal_rhino, "وحيد القرن"));
        dataArrayList.add(new Data(R.raw.sound_arabic_28, R.drawable.arabic_alphabet_28, R.drawable.arabic_animal_dragonfly, "يعسوب"));

        DataAdapter dataAdapter = new DataAdapter(this, dataArrayList, this);
        recyclerView.setAdapter(dataAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        releaseMedia();
        mediaPlayer = MediaPlayer.create(ArabicLettersActivity.this,
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