package com.example.naveen.wifidirect.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import com.example.naveen.wifidirect.presenter.PeersActivityPresenter;
import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;


public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private WifiP2pManager.PeerListListener mPeerListListener;
    private PeersActivityPresenter peersActivityPresenter;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       WifiP2pManager.PeerListListener peerListListener,PeersActivityPresenter presenter) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mPeerListListener=peerListListener;
        this.peersActivityPresenter =presenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {

                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG,"Discover peers on Success");
                    }
                    @Override
                    public void onFailure(int reasonCode) {
                        Log.d(TAG,"Discover peers on Failure");
                    }
                });

            } else {
                Log.d(TAG,"Wi-Fi P2P is not enabled");
            }

        }else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (mManager != null) {
                mManager.requestPeers(mChannel,mPeerListListener);
            }
            Log.d(TAG,"Came to WIFI_P2P_PEERS_CHANGED_ACTION in broadcast receiver");

        }
        else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            Log.d(TAG,"Came to WIFI_P2P_CONNECTION_CHANGED_ACTION");
            mManager.requestConnectionInfo(mChannel,peersActivityPresenter);
        }
        else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            Log.d(TAG,"Came to WIFI_P2P_THIS_DEVICE_CHANGED_ACTION");
        }
    }
}