package com.example.lastiti.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lastiti.database.note.NoteDatabase;
import com.example.lastiti.database.uer.User;
import com.example.lastiti.database.uer.UserDao;
import com.example.lastiti.database.uer.UserDatabase;

public class UserRepository {

    private final UserDao mUserDao;
    private final LiveData<User> mUser;

    public UserRepository(Application application) {
        UserDatabase db = Room.databaseBuilder(application, UserDatabase.class, "user_table").build();
//        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = mUserDao.getUser("");
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<User> getmUser() {
        return mUser;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(User user) {
        NoteDatabase.databaseWriteExecutor.execute(() -> mUserDao.insertAll(user));
    }
    public void delete(User user) {
        NoteDatabase.databaseWriteExecutor.execute(() -> mUserDao.delete(user));
    }
}

