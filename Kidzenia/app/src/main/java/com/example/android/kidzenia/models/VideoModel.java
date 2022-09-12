package com.example.android.kidzenia.models;

import android.net.Uri;

public class VideoModel {
    private final String title;
    private final String arabic_title;
    private final Uri path;

    public VideoModel(String title, String arabic_title, Uri path) {
        this.title = title;
        this.arabic_title = arabic_title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getArabic_title() {
        return arabic_title;
    }

    public Uri getPath() {
        return path;
    }
}
