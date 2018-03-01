package com.example.naveen.wifidirect.utils;

public class Constant {
    public interface Registration{
        String TAG="doodleblue";
        String EMPTY="";
        String CONNECTION_INFO_KEY="connection_info_key";
        String BUNDLE_KEY="bundle_key";
        String MESSAGE_KEY="message_key";
        String SEND="send";
        String RECEIVE="receive";
    }
    public interface ConnectionStatus{
        int AVAILABLE = 3;
        int CONNECTED = 0;
        int FAILED = 2;
        int INVITED = 1;
        int UNAVAILABLE = 4;
        String  available="Available";
        String connected="Connected";
        String failed="Failed";
        String invited="Invited";
        String unavailable="Unavailable";
    }
}
