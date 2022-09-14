package com.example.android.kidzenia.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kidzenia.R;
import com.example.android.kidzenia.adapters.LettersAdapter;
import com.example.android.kidzenia.models.LetterModel;

import java.util.ArrayList;

public class DisplayDataActivity extends AppCompatActivity
        implements LettersAdapter.OnListItemClickListener {

    private ArrayList<LetterModel> dataArrayList;
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
                }, 1200);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        music = MediaPlayer.create(this, R.raw.looped_music);
        music.setLooping(true);
        if (!MainActivity.isSoundsOn) music.setVolume(0f, 0f);

        backPressed = false;
        VISIBLE_BACKGROUND = false; // this line cancels the effect of the static field, but for more good user experience it will be kept

        background = findViewById(R.id.display_data_background);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        String dataType = getIntent().getStringExtra("dataType");

        if (!VISIBLE_BACKGROUND) {
            background.animate().alpha(1).setDuration(1000);
            VISIBLE_BACKGROUND = true;
            playMusic = false;

            int sound_id = -1;
            switch (dataType) {
                case "englishLetters":
                    sound_id = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC)
                            ? R.raw.sound_english_alphabet_in_arabic : R.raw.sound_the_abc_s;
                    break;
                case "englishNumbers":
                    sound_id = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC)
                            ? R.raw.sound_english_numbers_in_arabic : R.raw.sound_english_numbers_in_english;
                    break;
                case "arabicLetters":
                    sound_id = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC)
                            ? R.raw.sound_arabic_alphabet_in_arabic : R.raw.sound_arabic_alphabet_in_english;
                    break;
                case "arabicNumbers":
                    sound_id = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC)
                            ? R.raw.sound_arabic_numbers_in_arabic : R.raw.sound_arabic_numbers_in_english;
            }

            if (sound_id != -1) {
                mediaPlayer = MediaPlayer.create(this, sound_id);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        }

        dataArrayList = getIntentedDataList(dataType);

        LettersAdapter lettersAdapter = new LettersAdapter(this, dataArrayList, this);
        recyclerView.setAdapter(lettersAdapter);
    }

    /**************** START HELPERS ********************/
    private ArrayList<LetterModel> getIntentedDataList(String dataType) {
        switch (dataType) {
            case "englishLetters":
                return getEnglishLettersList();
            case "englishNumbers":
                return getEnglishNumbersList();
            case "arabicLetters":
                return getArabicLettersList();
            case "arabicNumbers":
                return getArabicNumbersList();
        }
        return getEnglishLettersList();
    }

    private ArrayList<LetterModel> getArabicLettersList() {
        ArrayList<LetterModel> arabicLettersList = new ArrayList<>();
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_1, R.drawable.arabic_alphabet_1, R.drawable.arabic_animal_rabbit, "أرنب"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_2, R.drawable.arabic_alphabet_2, R.drawable.arabic_animal_duck, "بطة"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_3, R.drawable.arabic_alphabet_3, R.drawable.arabic_animal_crocodile, "تمساح"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_4, R.drawable.arabic_alphabet_4, R.drawable.arabic_animal_fox, "ثعلب"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_5, R.drawable.arabic_alphabet_5, R.drawable.arabic_animal_camel, "جمل"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_6, R.drawable.arabic_alphabet_6, R.drawable.arabic_animal_horse, "حصان"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_7, R.drawable.arabic_alphabet_7, R.drawable.arabic_animal_pig, "خنزير"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_8, R.drawable.arabic_alphabet_8, R.drawable.arabic_animal_hen, "ديك"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_9, R.drawable.arabic_alphabet_9, R.drawable.arabic_animal_wolf, "ذئب"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_10, R.drawable.arabic_alphabet_10, R.drawable.arabic_animal_racoon, "راكون"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_11, R.drawable.arabic_alphabet_11, R.drawable.arabic_animal_giraf, "زرافة"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_12, R.drawable.arabic_alphabet_12, R.drawable.arabic_animal_fish, "سمكة"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_13, R.drawable.arabic_alphabet_13, R.drawable.arabic_animal_monkey, "شمبانزى"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_14, R.drawable.arabic_alphabet_14, R.drawable.arabic_animal_falcon, "صقر"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_15, R.drawable.arabic_alphabet_15, R.drawable.arabic_animal_frog, "ضفدع"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_16, R.drawable.arabic_alphabet_16, R.drawable.arabic_animal_peacock, "طاووس"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_17, R.drawable.arabic_alphabet_17, R.drawable.arabic_animal_antelope, "ظبى"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_18, R.drawable.arabic_alphabet_18, R.drawable.arabic_animal_bird, "عصفور"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_19, R.drawable.arabic_alphabet_19, R.drawable.arabic_animal_crow, "غراب"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_20, R.drawable.arabic_alphabet_20, R.drawable.arabic_animal_elephant, "فيل"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_21, R.drawable.arabic_alphabet_21, R.drawable.arabic_animal_cat, "قطة"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_22, R.drawable.arabic_alphabet_22, R.drawable.arabic_animal_dog, "كلب"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_23, R.drawable.arabic_alphabet_23, R.drawable.arabic_animal_lama, "لاما"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_24, R.drawable.arabic_alphabet_24, R.drawable.arabic_animal_sheep, "ماعز"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_25, R.drawable.arabic_alphabet_25, R.drawable.arabic_animal_tiger, "نمر"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_26, R.drawable.arabic_alphabet_26, R.drawable.arabic_animal_hoopoe, "هدهد"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_27, R.drawable.arabic_alphabet_27, R.drawable.arabic_animal_rhino, "وحيد القرن"));
        arabicLettersList.add(new LetterModel(R.raw.sound_arabic_28, R.drawable.arabic_alphabet_28, R.drawable.arabic_animal_dragonfly, "يعسوب"));
        return arabicLettersList;
    }

    private ArrayList<LetterModel> getArabicNumbersList() {
        ArrayList<LetterModel> arabicNumbersList = new ArrayList<>();
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_1_arabic, R.drawable.arabic_number_1, R.drawable.apple_1, "واحد"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_2_arabic, R.drawable.arabic_number_2, R.drawable.apples_2, "اثنان"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_3_arabic, R.drawable.arabic_number_3, R.drawable.apples_3, "ثلاثة"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_4_arabic, R.drawable.arabic_number_4, R.drawable.apples_4, "اربعة"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_5_arabic, R.drawable.arabic_number_5, R.drawable.apples_5, "خمسة"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_6_arabic, R.drawable.arabic_number_6, R.drawable.apples_6, "ستة"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_7_arabic, R.drawable.arabic_number_7, R.drawable.apples_7, "سبعة"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_8_arabic, R.drawable.arabic_number_8, R.drawable.apples_8, "ثمانية"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_9_arabic, R.drawable.arabic_number_9, R.drawable.apples_9, "تسعة"));
        arabicNumbersList.add(new LetterModel(R.raw.sound_number_10_arabic, R.drawable.arabic_number_10, R.drawable.apples_10, "عشرة"));
        return arabicNumbersList;
    }

    private ArrayList<LetterModel> getEnglishLettersList() {
        ArrayList<LetterModel> englishLettersList = new ArrayList<>();
        englishLettersList.add(new LetterModel(R.raw.sound_a, R.drawable.letter_a, R.drawable.animal_ant, "Ant"));
        englishLettersList.add(new LetterModel(R.raw.sound_b, R.drawable.letter_b, R.drawable.animal_bird, "Bird"));
        englishLettersList.add(new LetterModel(R.raw.sound_c, R.drawable.letter_c, R.drawable.animal_cat, "Cat"));
        englishLettersList.add(new LetterModel(R.raw.sound_d, R.drawable.letter_d, R.drawable.animal_dog, "Dog"));
        englishLettersList.add(new LetterModel(R.raw.sound_e, R.drawable.letter_e, R.drawable.animal_elephant, "Elephant"));
        englishLettersList.add(new LetterModel(R.raw.sound_f, R.drawable.letter_f, R.drawable.animal_frog, "Frog"));
        englishLettersList.add(new LetterModel(R.raw.sound_g, R.drawable.letter_g, R.drawable.animal_goat, "Goat"));
        englishLettersList.add(new LetterModel(R.raw.sound_h, R.drawable.letter_h, R.drawable.animal_horse, "Horse"));
        englishLettersList.add(new LetterModel(R.raw.sound_i, R.drawable.letter_i, R.drawable.animal_insect, "Insect"));
        englishLettersList.add(new LetterModel(R.raw.sound_j, R.drawable.letter_j, R.drawable.animal_jellyfish, "Jellyfish"));
        englishLettersList.add(new LetterModel(R.raw.sound_k, R.drawable.letter_k, R.drawable.animal_kangaroo, "Kangaroo"));
        englishLettersList.add(new LetterModel(R.raw.sound_l, R.drawable.letter_l, R.drawable.animal_lion, "Lion"));
        englishLettersList.add(new LetterModel(R.raw.sound_m, R.drawable.letter_m, R.drawable.animal_monkey, "Monkey"));
        englishLettersList.add(new LetterModel(R.raw.sound_n, R.drawable.letter_n, R.drawable.animal_newt, "Newt"));
        englishLettersList.add(new LetterModel(R.raw.sound_o, R.drawable.letter_o, R.drawable.animal_octopus, "Octopus"));
        englishLettersList.add(new LetterModel(R.raw.sound_p, R.drawable.letter_p, R.drawable.animal_pig, "Pig"));
        englishLettersList.add(new LetterModel(R.raw.sound_q, R.drawable.letter_q, R.drawable.animal_quail, "Quail"));
        englishLettersList.add(new LetterModel(R.raw.sound_r, R.drawable.letter_r, R.drawable.animal_rabbit, "Rabbit"));
        englishLettersList.add(new LetterModel(R.raw.sound_s, R.drawable.letter_s, R.drawable.animal_snake, "Snake"));
        englishLettersList.add(new LetterModel(R.raw.sound_t, R.drawable.letter_t, R.drawable.animal_tiger, "Tiger"));
        englishLettersList.add(new LetterModel(R.raw.sound_u, R.drawable.letter_u, R.drawable.animal_uguisu, "Uguisu"));
        englishLettersList.add(new LetterModel(R.raw.sound_v, R.drawable.letter_v, R.drawable.animal_vulture, "Vulture"));
        englishLettersList.add(new LetterModel(R.raw.sound_w, R.drawable.letter_w, R.drawable.animal_wolf, "Wolf"));
        englishLettersList.add(new LetterModel(R.raw.sound_x, R.drawable.letter_x, R.drawable.animal_xerus, "Xerus"));
        englishLettersList.add(new LetterModel(R.raw.sound_y, R.drawable.letter_y, R.drawable.animal_yak, "Yak"));
        englishLettersList.add(new LetterModel(R.raw.sound_z, R.drawable.letter_z, R.drawable.animal_zebra, "Zebra"));
        return englishLettersList;
    }

    private ArrayList<LetterModel> getEnglishNumbersList() {
        ArrayList<LetterModel> englishNumbersList = new ArrayList<>();
        englishNumbersList.add(new LetterModel(R.raw.sound_1, R.drawable.number_1, R.drawable.apple_1, "One"));
        englishNumbersList.add(new LetterModel(R.raw.sound_2, R.drawable.number_2, R.drawable.apples_2, "Two"));
        englishNumbersList.add(new LetterModel(R.raw.sound_3, R.drawable.number_3, R.drawable.apples_3, "Three"));
        englishNumbersList.add(new LetterModel(R.raw.sound_4, R.drawable.number_4, R.drawable.apples_4, "Four"));
        englishNumbersList.add(new LetterModel(R.raw.sound_5, R.drawable.number_5, R.drawable.apples_5, "Five"));
        englishNumbersList.add(new LetterModel(R.raw.sound_6, R.drawable.number_6, R.drawable.apples_6, "Six"));
        englishNumbersList.add(new LetterModel(R.raw.sound_7, R.drawable.number_7, R.drawable.apples_7, "Seven"));
        englishNumbersList.add(new LetterModel(R.raw.sound_8, R.drawable.number_8, R.drawable.apples_8, "Eight"));
        englishNumbersList.add(new LetterModel(R.raw.sound_9, R.drawable.number_9, R.drawable.apples_9, "Nine"));
        englishNumbersList.add(new LetterModel(R.raw.sound_10, R.drawable.number_10, R.drawable.apples_10, "Ten"));
        return englishNumbersList;
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

    /****************  END HELPERS ********************/

    @Override
    public void onItemClickListener(int position) {
        if (playMusic) {
            releaseMedia();
            mediaPlayer = MediaPlayer.create(this, dataArrayList.get(position).getRawID());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }
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
