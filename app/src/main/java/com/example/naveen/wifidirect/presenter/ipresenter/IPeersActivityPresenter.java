package com.example.naveen.wifidirect.presenter.ipresenter;


import com.example.naveen.wifidirect.adapters.singleton.PeersDetails;

public interface IPeersActivityPresenter extends IBasePresenter {
    void discoverPeers();
    void connectToPeer(PeersDetails peersDetails);
}
