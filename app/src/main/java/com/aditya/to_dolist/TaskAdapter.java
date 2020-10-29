package com.aditya.to_dolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditya.to_dolist.database.TaskEntry;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskEntry> mTaskEntryList;
    ClickListener listener;

    interface ClickListener{ void onClick(int id);}

    public TaskAdapter(ClickListener listener) {
        this.listener = listener;
    }

    public void refresh(List<TaskEntry> taskEntryList){
        this.mTaskEntryList= taskEntryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context ctx  = viewGroup.getContext();
        View v = LayoutInflater.from(ctx).inflate(R.layout.task_item,viewGroup,false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) { taskViewHolder.bind(i); }

    @Override
    public int getItemCount() { return mTaskEntryList.size(); }

    public List<TaskEntry> getTasks(){
        return mTaskEntryList;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mDescriptionTv, mDateTv, mPriorityTv;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mDescriptionTv  =itemView.findViewById(R.id.taskDescription);
            mPriorityTv  =itemView.findViewById(R.id.priorityTv);
            mDateTv  =itemView.findViewById(R.id.taskUpdatedAt);
        }

        void bind(int i){
            TaskEntry taskEntry = mTaskEntryList.get(i);
            mDateTv.setText(taskEntry.getUpdatedAt().toString());
            mDescriptionTv.setText(taskEntry.getDescription());
            mPriorityTv.setText(String.valueOf(taskEntry.getPriority()));
        }

        @Override
        public void onClick(View v) { listener.onClick(mTaskEntryList.get(getAdapterPosition()).getId()); }
    }

}
