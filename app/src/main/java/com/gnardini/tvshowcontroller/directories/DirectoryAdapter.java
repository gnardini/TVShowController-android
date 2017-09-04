package com.gnardini.tvshowcontroller.directories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnardini.tvshowcontroller.R;

import java.util.LinkedList;
import java.util.List;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder> {

    private DirectoryClickListener listener;
    private List<String> directories;

    public DirectoryAdapter(DirectoryClickListener listener) {
        this.listener = listener;
        this.directories = new LinkedList<>();
    }

    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.directory_view, parent, false);
        return new DirectoryViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {
        holder.setText(directories.get(position));
    }

    @Override
    public int getItemCount() {
        return directories.size();
    }

    public void setDirectories(List<String> directories) {
        this.directories = directories;
        notifyDataSetChanged();
    }

    static class DirectoryViewHolder extends RecyclerView.ViewHolder {

        private DirectoryClickListener listener;
        TextView directory;
        String text;

        public DirectoryViewHolder(View itemView, final DirectoryClickListener listener) {
            super(itemView);
            this.listener = listener;
            directory = itemView.findViewById(R.id.directory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDirectoryClicked(text);
                }
            });
        }

        public void setText(String text) {
            this.text = text;
            directory.setText(text);
        }
    }
}
