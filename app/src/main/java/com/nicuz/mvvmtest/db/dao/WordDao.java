package com.nicuz.mvvmtest.db.dao;

import com.nicuz.mvvmtest.db.entity.Word;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("DELETE FROM WORDS")
    void deleteAll();

    @Query("SELECT * FROM WORDS ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT rowid FROM WORDS WHERE word=:word")
    int getRowid(String word);
}
