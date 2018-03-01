package com.example.naveen.wifidirect.adapters.singleton;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable;

public class PeersDetails implements Parcelable{
    private WifiP2pDevice wifiP2pDevice;
    private String status;

    public PeersDetails() {

    }


    protected PeersDetails(Parcel in) {
        wifiP2pDevice = in.readParcelable(WifiP2pDevice.class.getClassLoader());
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(wifiP2pDevice, flags);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PeersDetails> CREATOR = new Creator<PeersDetails>() {
        @Override
        public PeersDetails createFromParcel(Parcel in) {
            return new PeersDetails(in);
        }

        @Override
        public PeersDetails[] newArray(int size) {
            return new PeersDetails[size];
        }
    };

    public WifiP2pDevice getWifiP2pDevice() {
        return wifiP2pDevice;
    }

    public void setWifiP2pDevice(WifiP2pDevice wifiP2pDevice) {
        this.wifiP2pDevice = wifiP2pDevice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
