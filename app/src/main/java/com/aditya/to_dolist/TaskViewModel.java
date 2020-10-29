package com.aditya.to_dolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aditya.to_dolist.database.TaskDatabase;
import com.aditya.to_dolist.database.TaskEntry;

public class TaskViewModel extends ViewModel {

    private LiveData<TaskEntry> task;

    public TaskViewModel(TaskDatabase mDb, int mTaskId) {
        task = mDb.taskDao().loadTaskByID(mTaskId);
    }

    public LiveData<TaskEntry> getTask() {
        return task;
    }
}
