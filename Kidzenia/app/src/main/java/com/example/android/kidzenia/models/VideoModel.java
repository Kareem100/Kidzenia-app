package com.example.android.kidzenia.models;

import android.net.Uri;

public class VideoModel {
    private final String title;
    private final Uri path;

    public VideoModel(String title, Uri path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public Uri getPath() {
        return path;
    }

}
