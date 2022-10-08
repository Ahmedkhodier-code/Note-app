package com.example.lastiti.database.note;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lastiti.repo.NoteRepository;

import java.util.List;
import java.util.Objects;


public class NoteViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> flag = new MutableLiveData<>();
    private final MutableLiveData<Boolean> flagDelete = new MutableLiveData<>();

    private final NoteRepository mRepository;
    private final LiveData<List<Note>> mAllNotes;

    public NoteViewModel(Application application) {
        super(application);
        flag.setValue(false);
        flagDelete.setValue(false);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public LiveData<Boolean> getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Boolean b) {
        flagDelete.setValue(b);
    }

    public void BTNPressed() {
        flag.setValue(true);
    }

    public LiveData<Boolean> getFlag() {
        return flag;
    }

    public void setFlag(Boolean b) {
        flag.setValue(b);
    }


    public LiveData<List<Note>> getmAllNotes() {
        List<Note> Note = mAllNotes.getValue();
        Objects.requireNonNull(Note).add(new Note("", "", "", 0));
        LiveData<List<Note>> newNote = mAllNotes;
        return mAllNotes;
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void delete(Note note) {
        mRepository.delete(note);
    }

    public void update(Note note, Note note1) {
        mRepository.delete(note);
        mRepository.insert(note1);
    }

    private final MutableLiveData<Note> selectedItem = new MutableLiveData<>();

    public void selectItem(Note item) {
        selectedItem.setValue(item);
    }

    public LiveData<Note> getSelectedItem() {
        return selectedItem;
    }

}