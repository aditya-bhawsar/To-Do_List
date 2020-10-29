package com.aditya.to_dolist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase sInstance;
    private static final String DATABASE_NAME = "todolist";
    private static final Object LOCK = new Object();

    public static TaskDatabase getInstance(Context ctx){
        if(sInstance==null){ synchronized (LOCK){ sInstance = Room.databaseBuilder(ctx.getApplicationContext(),TaskDatabase.class, TaskDatabase.DATABASE_NAME).build(); } }
        return sInstance;
    }

    public abstract TaskDao taskDao();
}