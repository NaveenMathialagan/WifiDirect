package com.example.naveen.wifidirect.adapters.listener;



public interface IPeersNameListListener<PeersDetails> extends BaseRecyclerAdapterListener<PeersDetails>{
    void changeStatus(int position);
}
