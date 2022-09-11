package com.example.android.kidzenia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.kidzenia.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private ImageView animationImg, logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        animationImg = findViewById(R.id.splash_hello_animation);
        logo = findViewById(R.id.splash_logo);

        Glide.with(this).asGif().load(R.drawable.back_to_school_anim).into(animationImg);

        animationImg.setAlpha(0f);
        logo.setAlpha(0f);

        animationImg.animate().alpha(1f).setDuration(1000);

        new Handler().postDelayed(() -> {
            animationImg.animate().alpha(0f).setDuration(2000);
            logo.animate().alpha(1f).setDuration(2000);
        }, 4000);

        new Handler().postDelayed(() -> {
            Pair<View, String> pairs = new Pair<>(logo, "logo_image");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, pairs);
            startActivity(new Intent(SplashActivity.this, MainActivity.class), options.toBundle());
            finish();
        }, 6000);

    }
}