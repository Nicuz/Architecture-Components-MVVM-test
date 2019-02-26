package com.nicuz.mvvmtest;

import android.app.Application;
import android.os.AsyncTask;

import com.nicuz.mvvmtest.db.entity.Word;
import com.nicuz.mvvmtest.db.dao.WordDao;
import com.nicuz.mvvmtest.db.WordRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class WordRepository {

    private static WordRepository mInstance;
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public static synchronized WordRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new WordRepository(application);
        }
        return mInstance;
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new WordRepository.insertWordAsyncTask(mWordDao).execute(word);
    }

    public int getRowid(String word) {

        int rowid = -1;

        try {
            rowid = new getRowidAsyncTask(mWordDao).execute(word).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rowid;
    }

    public void deleteWord(Word word) {
        new deleteWordAsyncTask(mWordDao).execute(word);
    }

    private static class insertWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsynkTaskDao;

        insertWordAsyncTask(WordDao dao){
            mAsynkTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsynkTaskDao.insert(words[0]);
            return null;
        }
    }

    private static class getRowidAsyncTask extends AsyncTask<String, Void, Integer> {
        private WordDao mAsynkTaskDao;

        getRowidAsyncTask(WordDao dao){
            mAsynkTaskDao = dao;
        }

        @Override
        protected Integer doInBackground(String... words) {
            return mAsynkTaskDao.getRowid(words[0]);
        }
    }

    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsynkTaskDao;

        deleteWordAsyncTask(WordDao dao){
            mAsynkTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsynkTaskDao.delete(words[0]);
            return null;
        }
    }
}
