package com.example.android.kidzenia.models;

public class LanguageModel {

    private String language;
    private final int countryFlag;

    public LanguageModel(String language, int countryFlag) {
        this.language = language;
        this.countryFlag = countryFlag;
    }

    public String getLanguage() {
        return language;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
