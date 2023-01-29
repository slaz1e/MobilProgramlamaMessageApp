package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesssageAdapter extends RecyclerView.Adapter<MessageViewHolder>{
    Context context;
    List<MessageClass>messageClassList;

    public MesssageAdapter(Context context, List<MessageClass> messageClassList) {
        this.context = context;
        this.messageClassList = messageClassList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.mitems,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.message.setText(messageClassList.get(position).getMessage());
        holder.name.setText(messageClassList.get(position).getName());
        holder.position.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return messageClassList.size();
    }
}
