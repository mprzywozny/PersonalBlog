package com.example.personalblog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.contentField.setText(post.getContent());
        Date date = new Date(post.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm", //format timestamp so it shows correctly
                Locale.getDefault()
        );
        holder.timeStampField.setText(formatter.format(date));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView contentField, timeStampField;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            contentField = itemView.findViewById(R.id.contentField);
            timeStampField = itemView.findViewById(R.id.timeStampField);
        }

    }
}