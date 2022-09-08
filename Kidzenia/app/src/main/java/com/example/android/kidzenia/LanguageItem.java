package com.example.android.kidzenia;

public class LanguageItem {

    private String language;
    private final int countryFlag;

    public LanguageItem(String language, int countryFlag) {
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
