package com.koto.roomwordsamplejava;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<List<Word>> allWords;

    public WordViewModel(@NonNull final Application application) {
        super(application);

        repository = new WordRepository(application);
        allWords = repository.getAllWords();
    }

    public void insert(Word word) {
        repository.insert(word);
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }
}
