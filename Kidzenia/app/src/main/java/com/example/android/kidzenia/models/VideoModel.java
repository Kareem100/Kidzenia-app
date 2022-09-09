package com.example.android.kidzenia.models;

import android.graphics.Bitmap;

public class VideoModel {
    private final String name;
    private final String path;
    private final Bitmap thumbnail;

    public VideoModel(String name, String path, Bitmap thumbnail) {
        this.name = name;
        this.path = path;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }
}
