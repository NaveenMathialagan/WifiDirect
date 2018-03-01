package com.example.naveen.wifidirect.adapters.listener;


public interface IMessageListListener<UserNotes> extends BaseRecyclerAdapterListener<UserNotes> {
    void closeItem(int pos);

}
