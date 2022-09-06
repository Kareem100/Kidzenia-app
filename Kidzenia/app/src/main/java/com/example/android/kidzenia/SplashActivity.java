package com.example.android.kidzenia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private ImageView background, animationImg, logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        background = findViewById(R.id.splash_background_1);
        animationImg = findViewById(R.id.splash_hello_animation);
        logo = findViewById(R.id.splash_logo);

        background.setAlpha(0f);
        animationImg.setAlpha(0f);
        logo.setAlpha(0f);

        background.animate().alpha(1f).setDuration(2000).setStartDelay(4000);
        animationImg.animate().alpha(1f).setDuration(1000);

        new Handler().postDelayed(() -> {
            background.animate().translationY(-2500).alpha(0f).setDuration(2000).setStartDelay(2000);
            logo.animate().alpha(1f).setDuration(2000).setStartDelay(3000);
            animationImg.setAlpha(0f);
        }, 6000);

        new Handler().postDelayed(() -> {
            Pair<View, String> pairs = new Pair<>(logo, "logo_image");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, pairs);
            startActivity(new Intent(SplashActivity.this, MainActivity.class), options.toBundle());
            finish();
        }, 13000);

    }
}