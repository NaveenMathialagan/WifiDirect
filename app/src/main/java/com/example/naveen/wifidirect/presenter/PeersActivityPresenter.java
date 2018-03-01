package com.example.naveen.wifidirect.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.PeersListAdapter;
import com.example.naveen.wifidirect.adapters.listener.IPeersNameListListener;
import com.example.naveen.wifidirect.adapters.singleton.PeersDetails;
import com.example.naveen.wifidirect.presenter.ipresenter.IPeersActivityPresenter;
import com.example.naveen.wifidirect.receiver.WiFiDirectBroadcastReceiver;
import com.example.naveen.wifidirect.view.iview.IPeersActivityView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.os.Looper.getMainLooper;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.CONNECTED;
import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;


public class PeersActivityPresenter extends BasePresenter<IPeersActivityView> implements IPeersActivityPresenter,WifiP2pManager.ConnectionInfoListener {


    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    private List<WifiP2pDevice> mDevicelist;
    private PeersListAdapter mPeersListAdapter;

    private IPeersNameListListener<PeersDetails> iPeersNameListListener=new IPeersNameListListener<PeersDetails>() {
        @Override
        public void changeStatus(int position) {
            List<PeersDetails> peersDetailsList = mPeersListAdapter.getList();
            PeersDetails peersDetails=peersDetailsList.get(position);
            if (peersDetails.getWifiP2pDevice()!=null){
                Log.d(TAG,peersDetails.getStatus());
                mPeersListAdapter.notifyDataSetChanged();
                if (peersDetails.getWifiP2pDevice().status!=CONNECTED){
                    connectToPeer(peersDetails);
                }else {
                    iView.showMessage(iView.getActivity().getString(R.string.already_connected));
                }
            }
        }
        @Override
        public void onClickItem(PeersDetails data) {

        }
    };

    private WifiP2pManager.PeerListListener  mPeerListListener=new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
            Log.d(TAG,"On Peers Available list "+wifiP2pDeviceList.getDeviceList().size());
            Collection<WifiP2pDevice> wifiP2pDevices= wifiP2pDeviceList.getDeviceList();
            mDevicelist=new ArrayList<>(wifiP2pDevices);
            mPeersListAdapter.clearData();
            if (mDevicelist.size()>0) {
                    for (WifiP2pDevice wifiP2pDevice : mDevicelist) {
                        Log.d(TAG,wifiP2pDevice.deviceAddress);
                        Log.d(TAG,wifiP2pDevice.status+"");
                        PeersDetails peersDetails = new PeersDetails();
                        peersDetails.setWifiP2pDevice(wifiP2pDevice);
                        peersDetails.setStatus(iView.getCodeSnippet().getStatus(wifiP2pDevice.status));
                        mPeersListAdapter.addItem(peersDetails);
                        mPeersListAdapter.notifyDataSetChanged();
                        Log.d(TAG, wifiP2pDevice.deviceName);
                    }
            }else {
                mPeersListAdapter.addItem(setEmptyData());
            }
        }
    };

    public PeersActivityPresenter(IPeersActivityView iView) {
        super(iView);

        mDevicelist= new ArrayList<>();


        mManager = (WifiP2pManager) iView.getActivity().getSystemService(Context.WIFI_P2P_SERVICE);

        mChannel = mManager.initialize(iView.getActivity(), getMainLooper(), null);

        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel,mPeerListListener,this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        setPeerListAdapter();
        discoverPeers();
    }

    @Override
    public void onCreatePresenter(Bundle bundle) {

    }

    @Override
    public void onPausePresenter() {
        super.onPausePresenter();
        iView.getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onResumePresenter() {
        super.onResumePresenter();
        iView.getActivity().registerReceiver(mReceiver,mIntentFilter);
    }

    @Override
    public void onBackPressedPresenter() {

    }

    @Override
    public void onActivityResultPresenter(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void discoverPeers(){
          mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                if (mManager != null) {
                    mManager.requestPeers(mChannel,mPeerListListener);
                }
                Log.d(TAG, "On Success Peers Discovered");
            }
            @Override
            public void onFailure(int reasonCode) {
                Log.d(TAG, "On Failure");
            }
        });
    }

    @Override
    public void connectToPeer(final PeersDetails peersDetails) {
        if (mDevicelist.size()!=0) {
            final WifiP2pDevice device = peersDetails.getWifiP2pDevice();
            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = device.deviceAddress;
            config.wps.setup = WpsInfo.PBC;
            config.groupOwnerIntent = 4;
            mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {

                    Log.d(TAG,device.status+"");
                    Log.d(TAG,"Connected to Peer");
                    peersDetails.setStatus(iView.getCodeSnippet().getStatus(device.status));
                    mPeersListAdapter.notifyDataSetChanged();

                }
                @Override
                public void onFailure(int reason) {
                    peersDetails.setStatus(iView.getCodeSnippet().getStatus(device.status));
                    mPeersListAdapter.notifyDataSetChanged();
                    iView.showMessage(iView.getActivity().getString(R.string.status_not_connected));
                }
            });
        }else {
            iView.showMessage(iView.getActivity().getString(R.string.no_peer_found));
        }
    }

    private void setPeerListAdapter(){
        List<PeersDetails> peersDetails=new ArrayList<>();
        peersDetails.add(setEmptyData());
        mPeersListAdapter=new PeersListAdapter(peersDetails,iPeersNameListListener);
        iView.setPeersListAdapter(mPeersListAdapter);
    }

    private PeersDetails setEmptyData()
    {
        PeersDetails peersObject=new PeersDetails();
        peersObject.setWifiP2pDevice(null);
        peersObject.setStatus(iView.getActivity().getString(R.string.none));
        return peersObject;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
        Log.d(TAG,"came to Connection info available");
        if (wifiP2pInfo.groupOwnerAddress!=null){
            if (wifiP2pInfo.isGroupOwner){
                iView.navigateToChatActivity(true,"");
            } else {
                iView.navigateToChatActivity(false,wifiP2pInfo.groupOwnerAddress.getHostAddress());
            }
            Log.d(TAG,wifiP2pInfo.groupOwnerAddress.getHostAddress());
        }else {
            Log.d(TAG,"Inetaddress is null");
        }
    }

}
