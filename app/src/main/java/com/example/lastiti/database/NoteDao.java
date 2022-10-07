package com.example.lastiti.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY uid ASC")
    LiveData<List<Note>> getAll();

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM notes_table")
    void deleteAll();
}
