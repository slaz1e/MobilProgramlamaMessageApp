package com.example.chatapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    TextView message,name,position;
    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        message=itemView.findViewById(R.id.mMessage);
        name=itemView.findViewById(R.id.mName);
        position=itemView.findViewById(R.id.mPosition);
    }
}
