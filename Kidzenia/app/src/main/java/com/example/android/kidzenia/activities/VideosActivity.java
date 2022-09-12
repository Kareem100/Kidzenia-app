package com.example.android.kidzenia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

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

        String vid_name_1 = getString(R.string.english_alphabet_song_1);
        String vid_name_2 = getString(R.string.english_numbers_song_1);
        String vid_name_3 = getString(R.string.arabic_alphabet_song_1);
        String vid_name_4 = getString(R.string.arabic_numbers_song_1);
        if (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC) {
            vid_name_1 = "اغنية الحروف الابجدية الانجليزية #1";
            vid_name_2 = "اغنية الارقام الانجليزية #1";
            vid_name_3 = "اغنية الحروف الابجدية العربية #1";
            vid_name_4 = "اغنية الارقام العربية #1";
        }

        videoModelArrayList.add(getNewVideoModel(vid_name_1, R.raw.english_alphabet_song));
        videoModelArrayList.add(getNewVideoModel(vid_name_2, R.raw.english_numbers_song));
        videoModelArrayList.add(getNewVideoModel(vid_name_3, R.raw.arabic_alphabet_song));
        videoModelArrayList.add(getNewVideoModel(vid_name_4, R.raw.arabic_numbers_song));

        VideosAdapter videosAdapter = new VideosAdapter(this, videoModelArrayList, this);
        videosRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        videosRecyclerView.setAdapter(videosAdapter);
    }

    private VideoModel getNewVideoModel(String name, int raw_song_id) {
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + raw_song_id);
        // Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(path.getPath(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        return new VideoModel(name, path/*, thumbnail*/);
    }

    @Override
    public void onVideoClick(int position) {
        Intent i = new Intent(VideosActivity.this, VideoPlayerActivity.class);
        i.putExtra("video_title", videoModelArrayList.get(position).getTitle());
        i.putExtra("video_path", videoModelArrayList.get(position).getPath().toString());
        startActivity(i);
    }
}