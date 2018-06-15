package com.koto.roomwordsamplejava;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull final SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract WordDao wordDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private WordDao wordDao;

        PopulateDbAsync(final WordRoomDatabase db) {
            this.wordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            wordDao.deleteAll();

            Word word = new Word("Hello");
            wordDao.insert(word);

            word = new Word("yellow");
            wordDao.insert(word);

            return null;
        }
    }
}
