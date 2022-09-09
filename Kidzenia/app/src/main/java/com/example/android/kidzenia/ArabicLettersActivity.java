package com.example.android.kidzenia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kidzenia.adapters.LettersAdapter;
import com.example.android.kidzenia.models.LetterModel;

import java.util.ArrayList;

public class ArabicLettersActivity extends AppCompatActivity
        implements LettersAdapter.OnListItemClickListener {

    private ArrayList<LetterModel> letterModelArrayList;
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

        letterModelArrayList = new ArrayList<>();
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_1, R.drawable.arabic_alphabet_1, R.drawable.arabic_animal_rabbit, "أرنب"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_2, R.drawable.arabic_alphabet_2, R.drawable.arabic_animal_duck, "بطة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_3, R.drawable.arabic_alphabet_3, R.drawable.arabic_animal_crocodile, "تمساح"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_4, R.drawable.arabic_alphabet_4, R.drawable.arabic_animal_fox, "ثعلب"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_5, R.drawable.arabic_alphabet_5, R.drawable.arabic_animal_camel, "جمل"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_6, R.drawable.arabic_alphabet_6, R.drawable.arabic_animal_horse, "حصان"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_7, R.drawable.arabic_alphabet_7, R.drawable.arabic_animal_pig, "خنزير"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_8, R.drawable.arabic_alphabet_8, R.drawable.arabic_animal_hen, "ديك"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_9, R.drawable.arabic_alphabet_9, R.drawable.arabic_animal_wolf, "ذئب"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_10, R.drawable.arabic_alphabet_10, R.drawable.arabic_animal_racoon, "راكون"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_11, R.drawable.arabic_alphabet_11, R.drawable.arabic_animal_giraf, "زرافة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_12, R.drawable.arabic_alphabet_12, R.drawable.arabic_animal_fish, "سمكة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_13, R.drawable.arabic_alphabet_13, R.drawable.arabic_animal_monkey, "شمبانزى"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_14, R.drawable.arabic_alphabet_14, R.drawable.arabic_animal_falcon, "صقر"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_15, R.drawable.arabic_alphabet_15, R.drawable.arabic_animal_frog, "ضفدع"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_16, R.drawable.arabic_alphabet_16, R.drawable.arabic_animal_peacock, "طاووس"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_17, R.drawable.arabic_alphabet_17, R.drawable.arabic_animal_antelope, "ظبى"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_18, R.drawable.arabic_alphabet_18, R.drawable.arabic_animal_bird, "عصفور"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_19, R.drawable.arabic_alphabet_19, R.drawable.arabic_animal_crow, "غراب"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_20, R.drawable.arabic_alphabet_20, R.drawable.arabic_animal_elephant, "فيل"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_21, R.drawable.arabic_alphabet_21, R.drawable.arabic_animal_cat, "قطة"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_22, R.drawable.arabic_alphabet_22, R.drawable.arabic_animal_dog, "كلب"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_23, R.drawable.arabic_alphabet_23, R.drawable.arabic_animal_lama, "لاما"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_24, R.drawable.arabic_alphabet_24, R.drawable.arabic_animal_sheep, "ماعز"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_25, R.drawable.arabic_alphabet_25, R.drawable.arabic_animal_tiger, "نمر"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_26, R.drawable.arabic_alphabet_26, R.drawable.arabic_animal_hoopoe, "هدهد"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_27, R.drawable.arabic_alphabet_27, R.drawable.arabic_animal_rhino, "وحيد القرن"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_arabic_28, R.drawable.arabic_alphabet_28, R.drawable.arabic_animal_dragonfly, "يعسوب"));

        LettersAdapter lettersAdapter = new LettersAdapter(this, letterModelArrayList, this);
        recyclerView.setAdapter(lettersAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        releaseMedia();
        mediaPlayer = MediaPlayer.create(ArabicLettersActivity.this,
                letterModelArrayList.get(position).getRawID());
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