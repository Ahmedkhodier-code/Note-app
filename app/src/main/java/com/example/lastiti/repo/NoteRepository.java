package com.example.lastiti.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lastiti.AppDatabase;
import com.example.lastiti.database.Note;
import com.example.lastiti.database.NoteDao;

import java.util.List;

public class NoteRepository {

    private final NoteDao mNoteDao;
    private final LiveData<List<Note>> mAllNotes;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public NoteRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "notes_table").build();
//        AppDatabase db = AppDatabase.getDatabase(application);
        mNoteDao = db.dogDao();
        mAllNotes = mNoteDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> mNoteDao.insertAll(note));
    }
    public void delete(Note note) {
        AppDatabase.databaseWriteExecutor.execute(() -> mNoteDao.delete(note));
    }
}
