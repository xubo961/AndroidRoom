package com.xubop961.palabrasroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PalabraDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Palabra palabra);

    @Delete
    void delete(Palabra palabra);

    @Query("DELETE FROM tabla_palabras")
    void deleteAll();

    @Query("SELECT * FROM tabla_palabras ORDER BY palabra ASC")
    LiveData<List<Palabra>> getPalabrasOrdenadas();
}
