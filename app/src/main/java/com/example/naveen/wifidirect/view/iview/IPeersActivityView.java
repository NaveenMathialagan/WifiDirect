package com.example.naveen.wifidirect.view.iview;


import android.widget.ArrayAdapter;

import com.example.naveen.wifidirect.adapters.PeersListAdapter;

public interface IPeersActivityView extends IBaseView {
    void setPeersListAdapter(PeersListAdapter peersListAdapter);
    void navigateToChatActivity(boolean isOwner,String ip);
}
