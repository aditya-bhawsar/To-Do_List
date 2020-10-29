package com.aditya.to_dolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.aditya.to_dolist.database.TaskDatabase;
import com.aditya.to_dolist.database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<TaskEntry>> tasks;

    public MainViewModel(@NonNull Application application) {
        super(application);
        TaskDatabase mDb = TaskDatabase.getInstance(this.getApplication());
        tasks = mDb.taskDao().loadAllTasks();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }
}
