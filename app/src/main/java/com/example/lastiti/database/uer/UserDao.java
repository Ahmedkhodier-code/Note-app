package com.example.lastiti.database.uer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users_table Where user_token = :token")
    LiveData<User> getUser(String token);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM users_table")
    void deleteAll();
}
