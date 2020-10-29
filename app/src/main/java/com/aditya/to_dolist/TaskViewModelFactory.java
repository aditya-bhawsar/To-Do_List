package com.aditya.to_dolist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aditya.to_dolist.database.TaskDatabase;

public class TaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TaskDatabase mDb;
    private final int mTaskId;

    public TaskViewModelFactory(TaskDatabase mDb, int mTaskId) {
        this.mDb = mDb;
        this.mTaskId = mTaskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TaskViewModel(mDb, mTaskId);
    }
}
