package com.example.android.kidzenia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android.kidzenia.R;

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private RelativeLayout customControlsLayout;
    private SeekBar seekBar;
    private TextView timeTextView;
    private Boolean isCustomOpen;
    private static final int _10SEC = 10000;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        isCustomOpen = true;

        String videoTitle = getIntent().getStringExtra("video_title");
        Uri videoPath = Uri.parse(getIntent().getStringExtra("video_path"));
        ((TextView) findViewById(R.id.player_video_title_text)).setText(videoTitle);

        seekBar = findViewById(R.id.player_seekbar);
        videoView = findViewById(R.id.player_video_view);
        timeTextView = findViewById(R.id.player_video_time);
        customControlsLayout = findViewById(R.id.video_custom_controls_layout);
        ImageButton playPauseButton = findViewById(R.id.player_play_pause_button);

        videoView.setVideoURI(videoPath);
        videoView.setOnPreparedListener(mediaPlayer -> {
            seekBar.setMax(videoView.getDuration());
            videoView.start();
        });

        findViewById(R.id.player_replay_button)
                .setOnClickListener(view -> videoView.seekTo(videoView.getCurrentPosition() - _10SEC));

        findViewById(R.id.player_forward_button)
                .setOnClickListener(view -> videoView.seekTo(videoView.getCurrentPosition() + _10SEC));

        playPauseButton.setOnClickListener(view -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                playPauseButton.setImageDrawable(getDrawable(R.drawable.ic_play_36));
            } else {
                videoView.start();
                playPauseButton.setImageDrawable(getDrawable(R.drawable.ic_pause_36));
            }
        });

        (findViewById(R.id.video_player_holder_frame)).setOnClickListener(view -> {
            if (isCustomOpen) {
                hideControls();
                isCustomOpen = false;
            } else {
                showControls();
                isCustomOpen = true;
            }
        });

        setHandler();
        setSeekBarProgressListener();
    }

    private void setHandler() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (videoView.getDuration() > 0) {
                    int curPos = videoView.getCurrentPosition();
                    seekBar.setProgress(curPos);
                    timeTextView.setText(formatTime(curPos));
                }
                handler.postDelayed(this, 0);
            }
        };
        handler.postDelayed(runnable, 500);
    }

    private void setSeekBarProgressListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBarChanged, int progress, boolean fromUser) {
                if (seekBarChanged.getId() == R.id.player_seekbar && fromUser) {
                    videoView.seekTo(progress);
                    timeTextView.setText(formatTime(videoView.getCurrentPosition()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String formatTime(int ms) {
        String formattedTime;
        int x, sec, min, hrs;
        x = ms / 1000;
        sec = x % 60;
        x /= 60;
        min = x % 60;
        x /= 60;
        hrs = x % 24;
        formattedTime = (hrs != 0) ?
                String.format("%02d", hrs) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec) :
                String.format("%02d", min) + ":" + String.format("%02d", sec);

        return formattedTime;
    }

    private void hideControls() {
        customControlsLayout.setVisibility(View.GONE);
        final Window window = this.getWindow();
        if (window == null) return;
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        View decorView = window.getDecorView();
        if (decorView != null) {
            int uiOption = decorView.getSystemUiVisibility();
            uiOption |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOption);
        }
    }

    private void showControls() {
        customControlsLayout.setVisibility(View.VISIBLE);
        final Window window = this.getWindow();
        if (window == null) return;
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        View decorView = window.getDecorView();
        if (decorView != null) {
            int uiOption = decorView.getSystemUiVisibility();
            uiOption &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
            uiOption &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            uiOption &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOption);
        }
    }

}