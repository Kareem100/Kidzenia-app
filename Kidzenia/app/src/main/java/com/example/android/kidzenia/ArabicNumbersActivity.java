package com.example.android.kidzenia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kidzenia.adapters.LettersAdapter;
import com.example.android.kidzenia.models.LetterModel;

import java.util.ArrayList;

public class ArabicNumbersActivity extends AppCompatActivity
        implements LettersAdapter.OnListItemClickListener {

    private ArrayList<LetterModel> letterModelArrayList;
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
                    ? R.raw.sound_arabic_numbers_in_arabic : R.raw.sound_arabic_numbers_in_english;
            mediaPlayer = MediaPlayer.create(this, sound_id);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        letterModelArrayList = new ArrayList<>();
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_1_arabic, R.drawable.arabic_number_1, R.drawable.apple_1, "واحد"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_2_arabic, R.drawable.arabic_number_2, R.drawable.apples_2, "اثنان"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_3_arabic, R.drawable.arabic_number_3, R.drawable.apples_3, "ثلاثة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_4_arabic, R.drawable.arabic_number_4, R.drawable.apples_4, "اربعة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_5_arabic, R.drawable.arabic_number_5, R.drawable.apples_5, "خمسة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_6_arabic, R.drawable.arabic_number_6, R.drawable.apples_6, "ستة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_7_arabic, R.drawable.arabic_number_7, R.drawable.apples_7, "سبعة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_8_arabic, R.drawable.arabic_number_8, R.drawable.apples_8, "ثمانية"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_9_arabic, R.drawable.arabic_number_9, R.drawable.apples_9, "تسعة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_number_10_arabic, R.drawable.arabic_number_10, R.drawable.apples_10, "عشرة"));

        LettersAdapter lettersAdapter = new LettersAdapter(this, letterModelArrayList, this);
        recyclerView.setAdapter(lettersAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        if (playMusic) {
            releaseMedia();
            mediaPlayer = MediaPlayer.create(this, letterModelArrayList.get(position).getRawID());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }
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