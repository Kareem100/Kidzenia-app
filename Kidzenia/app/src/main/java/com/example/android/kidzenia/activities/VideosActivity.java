package com.example.android.kidzenia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.android.kidzenia.R;
import com.example.android.kidzenia.adapters.VideosAdapter;
import com.example.android.kidzenia.models.VideoModel;

import java.util.ArrayList;

public class VideosActivity extends AppCompatActivity implements VideosAdapter.OnVideoClickListener {
    private ArrayList<VideoModel> videoModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        RecyclerView videosRecyclerView = findViewById(R.id.videos_recycler_view);
        videoModelArrayList = new ArrayList<>();

        videoModelArrayList.add(getNewVideoModel("English Alphabet Song #1", "اغنية الحروف الابجدية الانجليزية #1", R.raw.english_alphabet_song));
        videoModelArrayList.add(getNewVideoModel("English Numbers Song #1", "اغنية الارقام الانجليزية #1", R.raw.english_numbers_song));
        videoModelArrayList.add(getNewVideoModel("Arabic Alphabet Song #1", "اغنية الحروف الابجدية العربية #1", R.raw.arabic_alphabet_song));
        videoModelArrayList.add(getNewVideoModel("Arabic Numbers Song #1", "اغنية الارقام العربية #1", R.raw.arabic_numbers_song));

        VideosAdapter videosAdapter = new VideosAdapter(this, videoModelArrayList, this);
        videosRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        videosRecyclerView.setAdapter(videosAdapter);
    }

    private VideoModel getNewVideoModel(String title, String arabic_title, int raw_song_id) {
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + raw_song_id);
        // Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(path.getPath(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        return new VideoModel(title, arabic_title, path);
    }

    @Override
    public void onVideoClick(int position) {
        String title = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC) ?
                videoModelArrayList.get(position).getArabic_title() : videoModelArrayList.get(position).getTitle();
        Intent i = new Intent(VideosActivity.this, VideoPlayerActivity.class);
        i.putExtra("video_title", title);
        i.putExtra("video_path", videoModelArrayList.get(position).getPath().toString());
        startActivity(i);
    }
}