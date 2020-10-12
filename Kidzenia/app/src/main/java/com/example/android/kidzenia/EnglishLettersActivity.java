package com.example.android.kidzenia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EnglishLettersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView background;
    private ListView listView;
    protected static boolean VISIBLE_BACKGROUND = false;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
            if(background.getDrawable()!=null){
                background.animate().alpha(0).setDuration(1000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
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

        background = findViewById(R.id.english_letters_background);
        listView = findViewById(R.id.list_view);

        if(!VISIBLE_BACKGROUND) {
            background.animate().alpha(1).setDuration(1000);
            VISIBLE_BACKGROUND = true;
            mediaPlayer = mediaPlayer.create(this, R.raw.sound_the_abc_s);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }

        final ArrayList<Data> arrayList = new ArrayList<Data>();
        arrayList.add(new Data(R.raw.sound_a, R.drawable.letter_a, R.drawable.animal_ant, "Ant"));
        arrayList.add(new Data(R.raw.sound_b, R.drawable.letter_b, R.drawable.animal_bird, "Bird"));
        arrayList.add(new Data(R.raw.sound_c, R.drawable.letter_c, R.drawable.animal_cat, "Cat"));
        arrayList.add(new Data(R.raw.sound_d, R.drawable.letter_d, R.drawable.animal_dog, "Dog"));
        arrayList.add(new Data(R.raw.sound_e, R.drawable.letter_e, R.drawable.animal_elephant, "Elephant"));
        arrayList.add(new Data(R.raw.sound_f, R.drawable.letter_f, R.drawable.animal_frog, "Frog"));
        arrayList.add(new Data(R.raw.sound_g, R.drawable.letter_g, R.drawable.animal_goat, "Goat"));
        arrayList.add(new Data(R.raw.sound_h, R.drawable.letter_h, R.drawable.animal_horse, "Horse"));
        arrayList.add(new Data(R.raw.sound_i, R.drawable.letter_i, R.drawable.animal_insect, "Insect"));
        arrayList.add(new Data(R.raw.sound_j, R.drawable.letter_j, R.drawable.animal_jellyfish, "Jellyfish"));
        arrayList.add(new Data(R.raw.sound_k, R.drawable.letter_k, R.drawable.animal_kangaroo, "Kangaroo"));
        arrayList.add(new Data(R.raw.sound_l, R.drawable.letter_l, R.drawable.animal_lion, "Lion"));
        arrayList.add(new Data(R.raw.sound_m, R.drawable.letter_m, R.drawable.animal_monkey, "Monkey"));
        arrayList.add(new Data(R.raw.sound_n, R.drawable.letter_n, R.drawable.animal_newt, "Newt"));
        arrayList.add(new Data(R.raw.sound_o, R.drawable.letter_o, R.drawable.animal_octopus, "Octopus"));
        arrayList.add(new Data(R.raw.sound_p, R.drawable.letter_p, R.drawable.animal_pig, "Pig"));
        arrayList.add(new Data(R.raw.sound_q, R.drawable.letter_q, R.drawable.animal_quail, "Quail"));
        arrayList.add(new Data(R.raw.sound_r, R.drawable.letter_r, R.drawable.animal_rabbit, "Rabbit"));
        arrayList.add(new Data(R.raw.sound_s, R.drawable.letter_s, R.drawable.animal_snake, "Snake"));
        arrayList.add(new Data(R.raw.sound_t, R.drawable.letter_t, R.drawable.animal_tiger, "Tiger"));
        arrayList.add(new Data(R.raw.sound_u, R.drawable.letter_u, R.drawable.animal_uguisu, "Uguisu"));
        arrayList.add(new Data(R.raw.sound_v, R.drawable.letter_v, R.drawable.animal_vulture, "Vulture"));
        arrayList.add(new Data(R.raw.sound_w, R.drawable.letter_w, R.drawable.animal_wolf, "Wolf"));
        arrayList.add(new Data(R.raw.sound_x, R.drawable.letter_x, R.drawable.animal_xerus, "Xerus"));
        arrayList.add(new Data(R.raw.sound_y, R.drawable.letter_y, R.drawable.animal_yak, "Yak"));
        arrayList.add(new Data(R.raw.sound_z, R.drawable.letter_z, R.drawable.animal_zebra, "Zebra"));

        DataAdapter dataAdapter = new DataAdapter(this, arrayList);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                releaseMedia();
                mediaPlayer = mediaPlayer.create(EnglishLettersActivity.this, arrayList.get(position).getRawID());
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

    @Override
    public void onBackPressed() {
        finish();
    }
}