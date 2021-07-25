package com.e.projectmanager.rvadapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.projectmanager.DeskActivity;
import com.e.projectmanager.R;
import com.e.projectmanager.TasksActivity;
import com.e.projectmanager.retrofit.models.Desk;

import java.util.List;

public class DeskRVAdapter extends RecyclerView.Adapter <DeskRVAdapter.ViewHolder>{

    private final List<Desk> list;
    private final DeskActivity deskActivity;

    public DeskRVAdapter(List<Desk> list, DeskActivity deskActivity){
        this.list = list;
        this.deskActivity = deskActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.itemView.setTag(list.get(position).getId());
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
            int item = (int) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(deskActivity, TasksActivity.class);
            intent.putExtra("index", item);
            context.startActivity(intent);
        }
    };
}
