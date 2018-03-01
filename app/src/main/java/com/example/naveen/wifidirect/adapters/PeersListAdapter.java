package com.example.naveen.wifidirect.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.listener.IPeersNameListListener;
import com.example.naveen.wifidirect.adapters.singleton.PeersDetails;
import com.example.naveen.wifidirect.adapters.viewholder.PeersListViewHolder;

import java.util.List;

public class PeersListAdapter extends BaseRecyclerAdapter<PeersDetails,PeersListViewHolder>{

    private IPeersNameListListener<PeersDetails> iPeersNameListListener;
    public PeersListAdapter(List<PeersDetails> data,IPeersNameListListener<PeersDetails> iPeersNameListListener) {
        super(data);
        this.iPeersNameListListener=iPeersNameListListener;
    }

    @Override
    public PeersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.peers_content,parent,false);
        return new PeersListViewHolder(itemView,iPeersNameListListener);
    }
}