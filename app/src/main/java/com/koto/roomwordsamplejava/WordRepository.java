package com.koto.roomwordsamplejava;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application app) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(app);
        wordDao = db.wordDao();
        allWords = wordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(final Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        InsertAsyncTask(final WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}
