package com.example.ai_chat_bot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Variables for the array list and context.
    private final ArrayList<MessageModal> messageModalArrayList;
    private final Context context;

    // Constructor class.
    public MessageRVAdapter(ArrayList<MessageModal> messageModalArrayList, Context context) {
        this.messageModalArrayList = messageModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        // Inflate the appropriate layout based on viewType.
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg, parent, false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg, parent, false);
                return new BotViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid viewType: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Bind data to the view holder.
        MessageModal modal = messageModalArrayList.get(position);
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).userTV.setText(modal.getMessage());
        } else if (holder instanceof BotViewHolder) {
            ((BotViewHolder) holder).botTV.setText(modal.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of the array list.
        return messageModalArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Return the view type based on the sender.
        switch (messageModalArrayList.get(position).getSender()) {
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                throw new IllegalArgumentException("Invalid sender: " + messageModalArrayList.get(position).getSender());
        }
    }

    // ViewHolder for user messages.
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userTV;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }

    // ViewHolder for bot messages.
    public static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView botTV;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botTV = itemView.findViewById(R.id.idTVBot);
        }
    }
}
