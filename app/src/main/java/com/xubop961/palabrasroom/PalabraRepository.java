package com.xubop961.palabrasroom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraRepository {
    private PalabraDao mPalabraDao;
    private LiveData<List<Palabra>> mPalabras;

    PalabraRepository(Application application) {
        PalabraDB db = PalabraDB.getDatabase(application);
        mPalabraDao = db.palabraDao();
        mPalabras = mPalabraDao.getPalabrasOrdenadas();
    }

    LiveData<List<Palabra>> getAllPalabras() {
        return mPalabras;
    }

    void insert(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDao.insert(palabra);
        });
    }
}
