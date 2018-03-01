package com.example.naveen.wifidirect.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.listener.IMessageListListener;
import com.example.naveen.wifidirect.adapters.singleton.UserMessage;
import com.example.naveen.wifidirect.adapters.viewholder.MessageListViewHolder;

import java.util.List;


public class MessageListAdapter extends BaseRecyclerAdapter<UserMessage,MessageListViewHolder>{

    private IMessageListListener<UserMessage> iUserNoteListListener;
    public MessageListAdapter(List<UserMessage> data, IMessageListListener<UserMessage> iUserNoteListListener) {
        super(data);
        this.iUserNoteListListener=iUserNoteListListener;
    }

    @Override
    public MessageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_content,parent,false);
        return new MessageListViewHolder(itemView,iUserNoteListListener);
    }
}
