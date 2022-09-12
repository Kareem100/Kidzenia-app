package com.example.android.kidzenia.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.kidzenia.R;
import com.example.android.kidzenia.activities.MainActivity;
import com.example.android.kidzenia.models.VideoModel;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosViewHolder> {
    private final Context context;
    private final ArrayList<VideoModel> videoModelArrayList;
    private final OnVideoClickListener onVideoClickListener;

    public VideosAdapter(Context context, ArrayList<VideoModel> videoModelArrayList,
                         OnVideoClickListener onVideoClickListener) {
        this.context = context;
        this.videoModelArrayList = videoModelArrayList;
        this.onVideoClickListener = onVideoClickListener;
    }

    @NonNull
    @Override
    public VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_videos_list_item, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder, int position) {
        VideoModel videoModel = videoModelArrayList.get(position);
        String title = (MainActivity.currentLocale == MainActivity.CurrentLocale.ARABIC) ?
                videoModel.getArabic_title() : videoModel.getTitle();
        holder.bind(videoModel.getPath(), title);
    }

    @Override
    public int getItemCount() {
        return videoModelArrayList.size();
    }

    public class VideosViewHolder extends RecyclerView.ViewHolder {
        private final ImageView thumbnailImageView;
        private final TextView nameTextView;

        public VideosViewHolder(@NonNull View itemView) {
            super(itemView);
            this.thumbnailImageView = itemView.findViewById(R.id.video_thumbnail);
            this.nameTextView = itemView.findViewById(R.id.video_name_text);
            itemView.setOnClickListener(view -> onVideoClickListener.onVideoClick(getBindingAdapterPosition()));
        }

        public void bind(Uri videoPath, String videoName) {
            Glide.with(itemView.getContext()).load(videoPath).into(thumbnailImageView);
            nameTextView.setText(videoName);
        }
    }

    public interface OnVideoClickListener {
        void onVideoClick(int position);
    }
}
