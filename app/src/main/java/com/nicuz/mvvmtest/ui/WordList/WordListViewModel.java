package com.nicuz.mvvmtest.ui.WordList;

import android.app.Application;

import com.nicuz.mvvmtest.WordRepository;
import com.nicuz.mvvmtest.db.entity.Word;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordListViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<List<Word>> mAllWords;

    public WordListViewModel(Application application) {
        super(application);
        mWordRepository = WordRepository.getInstance(application);
        mAllWords = mWordRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    void deleteWord(Word word) {
        mWordRepository.deleteWord(word);
    }

    int getRowid(String word){
        return mWordRepository.getRowid(word);
    }
}
