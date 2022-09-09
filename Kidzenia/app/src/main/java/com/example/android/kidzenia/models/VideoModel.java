package com.example.android.kidzenia.models;

import android.net.Uri;

public class VideoModel {
    private final String name;
    private final Uri path;

    public VideoModel(String name, Uri path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public Uri getPath() {
        return path;
    }

}
