package com.example.android.kidzenia.models;

public class LetterModel {

    private final int letterNumberID;
    private final int animalAppleID;
    private final int rawID;
    private final String name;

    public LetterModel(int rawID, int letterNumberID, int animalAppleID, String name) {
        this.letterNumberID = letterNumberID;
        this.animalAppleID = animalAppleID;
        this.name = name;
        this.rawID = rawID;
    }

    public int getLetterNumberID() {
        return letterNumberID;
    }

    public int getAnimalAppleID() {
        return animalAppleID;
    }

    public int getRawID() {
        return rawID;
    }

    public String getName() {
        return name;
    }
}
