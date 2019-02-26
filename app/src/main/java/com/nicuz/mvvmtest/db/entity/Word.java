package com.nicuz.mvvmtest.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WORDS")
public class Word {

    @PrimaryKey
    @NonNull
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
}
