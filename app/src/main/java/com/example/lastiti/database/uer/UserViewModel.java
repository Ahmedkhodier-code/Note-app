package com.example.lastiti.database.uer;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.lastiti.repo.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository mRepository;
    private final LiveData<User> mUser;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mUser = mRepository.getmUser();
    }


    public LiveData<User> getmUser() {
        return mUser;
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public void delete(User user) {
        mRepository.delete(user);
    }

    public void update(User user , User user1) {
        mRepository.delete(user);
        mRepository.insert(user1);
    }
}