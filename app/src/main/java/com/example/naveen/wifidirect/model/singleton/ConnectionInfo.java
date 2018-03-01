package com.example.naveen.wifidirect.model.singleton;


import android.os.Parcel;
import android.os.Parcelable;

public class ConnectionInfo implements Parcelable{
    private boolean isOwner;
    private String ipAddress;

    public ConnectionInfo(){

    }
    private ConnectionInfo(Parcel in) {
        isOwner = in.readByte() != 0;
        ipAddress = in.readString();
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public static final Creator<ConnectionInfo> CREATOR = new Creator<ConnectionInfo>() {
        @Override
        public ConnectionInfo createFromParcel(Parcel in) {
            return new ConnectionInfo(in);
        }

        @Override
        public ConnectionInfo[] newArray(int size) {
            return new ConnectionInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isOwner ? 1 : 0));
        parcel.writeString(ipAddress);
    }
}
