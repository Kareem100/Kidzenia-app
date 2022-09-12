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

        loadRawVideos();

        VideosAdapter videosAdapter = new VideosAdapter(this, videoModelArrayList, this);
        videosRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        videosRecyclerView.setAdapter(videosAdapter);
    }

    private void loadRawVideos() {
        videoModelArrayList.add(getNewVideoModel("English Alphabet Song", "اغنية الحروف الابجدية الانجليزية", R.raw.english_alphabet_song));
        videoModelArrayList.add(getNewVideoModel("English Numbers Song", "اغنية الارقام الانجليزية", R.raw.english_numbers_song));
        videoModelArrayList.add(getNewVideoModel("English Alphabet Education", "الحروف الابجدية الانجليزية التعليمية", R.raw.english_arabic_alphabet_educ));
        videoModelArrayList.add(getNewVideoModel("English Days of the Week Song", "اغنية ايام الاسبوع الانجليزية", R.raw.english_days_of_the_week_song));
        videoModelArrayList.add(getNewVideoModel("English Never Give up Animation", "لا تستسلم ابدا (صور متحركة انجليزية)", R.raw.english_never_give_up_animation));

        videoModelArrayList.add(getNewVideoModel("Arabic Alphabet Song", "اغنية الحروف الابجدية العربية", R.raw.arabic_alphabet_song));
        videoModelArrayList.add(getNewVideoModel("Arabic Numbers Song", "اغنية الارقام العربية", R.raw.arabic_numbers_song));
        videoModelArrayList.add(getNewVideoModel("Arabic Alphabet Train Song", "اغنية قطار الحروف الابجدية العربية", R.raw.arabic_alphabet_train_song));
        videoModelArrayList.add(getNewVideoModel("Arabic Greedy Mouse Animation", "الفأر الطماع (صور متحركة عربية)", R.raw.arabic_greedy_mouse_animation));
        videoModelArrayList.add(getNewVideoModel("Arabic Clean is Faith Animation", "النظافة من الايمان (صور متحركة عربية)", R.raw.arabic_clean_is_faith_animation));
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