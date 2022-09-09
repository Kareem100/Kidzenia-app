package com.example.android.kidzenia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.android.kidzenia.adapters.LettersAdapter;
import com.example.android.kidzenia.models.LetterModel;

import java.util.ArrayList;

public class EnglishLettersActivity extends AppCompatActivity
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
                    ? R.raw.sound_english_alphabet_in_arabic : R.raw.sound_the_abc_s ;
            mediaPlayer = MediaPlayer.create(this, sound_id);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        letterModelArrayList = new ArrayList<>();
        letterModelArrayList.add(new LetterModel(R.raw.sound_a, R.drawable.letter_a, R.drawable.animal_ant, "Ant"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_b, R.drawable.letter_b, R.drawable.animal_bird, "Bird"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_c, R.drawable.letter_c, R.drawable.animal_cat, "Cat"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_d, R.drawable.letter_d, R.drawable.animal_dog, "Dog"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_e, R.drawable.letter_e, R.drawable.animal_elephant, "Elephant"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_f, R.drawable.letter_f, R.drawable.animal_frog, "Frog"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_g, R.drawable.letter_g, R.drawable.animal_goat, "Goat"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_h, R.drawable.letter_h, R.drawable.animal_horse, "Horse"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_i, R.drawable.letter_i, R.drawable.animal_insect, "Insect"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_j, R.drawable.letter_j, R.drawable.animal_jellyfish, "Jellyfish"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_k, R.drawable.letter_k, R.drawable.animal_kangaroo, "Kangaroo"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_l, R.drawable.letter_l, R.drawable.animal_lion, "Lion"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_m, R.drawable.letter_m, R.drawable.animal_monkey, "Monkey"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_n, R.drawable.letter_n, R.drawable.animal_newt, "Newt"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_o, R.drawable.letter_o, R.drawable.animal_octopus, "Octopus"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_p, R.drawable.letter_p, R.drawable.animal_pig, "Pig"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_q, R.drawable.letter_q, R.drawable.animal_quail, "Quail"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_r, R.drawable.letter_r, R.drawable.animal_rabbit, "Rabbit"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_s, R.drawable.letter_s, R.drawable.animal_snake, "Snake"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_t, R.drawable.letter_t, R.drawable.animal_tiger, "Tiger"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_u, R.drawable.letter_u, R.drawable.animal_uguisu, "Uguisu"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_v, R.drawable.letter_v, R.drawable.animal_vulture, "Vulture"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_w, R.drawable.letter_w, R.drawable.animal_wolf, "Wolf"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_x, R.drawable.letter_x, R.drawable.animal_xerus, "Xerus"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_y, R.drawable.letter_y, R.drawable.animal_yak, "Yak"));
        letterModelArrayList.add(new LetterModel(R.raw.sound_z, R.drawable.letter_z, R.drawable.animal_zebra, "Zebra"));

        LettersAdapter lettersAdapter = new LettersAdapter(this, letterModelArrayList, this);
        recyclerView.setAdapter(lettersAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        releaseMedia();
        mediaPlayer = MediaPlayer.create(this, letterModelArrayList.get(position).getRawID());
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