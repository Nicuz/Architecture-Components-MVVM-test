package com.nicuz.mvvmtest.ui.AddWord;

import android.app.Application;

import com.nicuz.mvvmtest.db.entity.Word;
import com.nicuz.mvvmtest.WordRepository;

import androidx.lifecycle.AndroidViewModel;

public class AddWordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;

    public AddWordViewModel(Application application){
        super(application);
        mWordRepository = WordRepository.getInstance(application);
    }

    public void insert(Word word) {
        mWordRepository.insert(word);
    }
}
