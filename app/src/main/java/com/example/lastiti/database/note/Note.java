package com.example.lastiti.database.note;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.lastiti.recyclerViewFragment.MyListAdapterLinear;

@Entity(tableName = "notes_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "note_title")
    public String title;
    @ColumnInfo(name = "note_desc")
    public String description;
    @ColumnInfo(name = "note_time")
    public String time;
    @ColumnInfo(name = "image")
    public int Image;
    public Boolean Footer;


    public Note(String title, String description, String time, int Image) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.Image = Image;
    }

    public Note(boolean Footer) {
        this.Footer = Footer;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    public Note getNote() {
        return new Note(title, description, time, Image);
    }
}
