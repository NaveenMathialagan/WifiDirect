package com.example.naveen.wifidirect.view.iview;


import com.example.naveen.wifidirect.adapters.MessageListAdapter;

public interface IChatActivityView extends IBaseView {
    void setMessageListAdapter(MessageListAdapter messageListAdapter);
    void setConnectionType();
    void scrollToLast(int position);
}
