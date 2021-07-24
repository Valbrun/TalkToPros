package com.wv.talktopros;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{
    Context context;
    ArrayList<MessageFormat>messageArrayList;

    public MessageAdapter(Context context, ArrayList<MessageFormat> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.message_format,parent,false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.MessageViewHolder holder, int position) {
        MessageFormat message;
        message = messageArrayList.get(position);
        holder.message.setText(message.message);
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        public MessageViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
        }
    }
}