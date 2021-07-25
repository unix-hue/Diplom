package com.e.projectmanager.rvadapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.projectmanager.R;
import com.e.projectmanager.TaskActivity;
import com.e.projectmanager.TasksActivity;
import com.e.projectmanager.models.Task;

import java.util.List;

public class TasksRVAdapter extends RecyclerView.Adapter <TasksRVAdapter.ViewHolder>{

    private List<Task> list;
    private TasksActivity tasksActivity;

    public TasksRVAdapter(List<Task> list, TasksActivity tasksActivity){
        this.list = list;
        this.tasksActivity = tasksActivity;
    }

    @NonNull
    @Override
    public TasksRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksRVAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        list.get(position).setIndex(position);
        holder.itemView.setTag(list.get(position));
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.desk_item_text);
        }
    }

    final private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Task item = (Task) view.getTag();
            Context context = view.getContext();

            Intent intent = new Intent(tasksActivity, TaskActivity.class);
            intent.putExtra("task", item.getName());
            intent.putExtra("desc", item.getDescription());
            intent.putExtra("worker", item.getWorker());
            intent.putExtra("index", item.getIndex());

            context.startActivity(intent);
        }
    };
}
