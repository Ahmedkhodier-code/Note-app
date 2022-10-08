package com.example.lastiti.database.uer;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "user_name")
    public String name;
    @ColumnInfo(name = "note_password")
    public String password;
    @ColumnInfo(name = "user_token")
    public String token;
    @ColumnInfo(name = "image")
    public int Image;


    public User(String name, String password, String token, int Image) {
        this.name = name;
        this.password = password;
        this.token = token;
        this.Image = Image;
    }

    public String getName() {
        return name;
    }


    public void setTitle(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    @NonNull
    public User getUser() {
        return new User(name, password, token, Image);
    }
}
