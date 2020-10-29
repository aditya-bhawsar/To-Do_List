package com.aditya.to_dolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.aditya.to_dolist.database.TaskDatabase;
import com.aditya.to_dolist.database.TaskEntry;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ClickListener {

    RecyclerView mRvTasks;
    TaskAdapter mAdapter;

    TaskDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvTasks = findViewById(R.id.rv_tasks);
        mAdapter = new TaskAdapter(this);

        mRvTasks.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRvTasks.addItemDecoration(decoration);

        mRvTasks.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                mDb.taskDao().deleteTask(mAdapter.getTasks().get(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(mRvTasks);

        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTaskIntent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(addTaskIntent);
            }
        });

        mDb= TaskDatabase.getInstance(getApplicationContext());
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntry> taskEntryList) {
                mAdapter.refresh(taskEntryList);
            }
        });
    }

    @Override
    public void onClick(int id) {
        Intent updateTaskIntent = new Intent(MainActivity.this, TaskActivity.class);
        updateTaskIntent.putExtra(TaskActivity.EXTRA_TASK_ID, id);
        startActivity(updateTaskIntent);
    }
}
