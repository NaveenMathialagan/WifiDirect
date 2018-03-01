package com.example.naveen.wifidirect.model.thread;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;


public class MessageSenderThread extends Thread {
    private Socket mSocket;
    private String message;
    public MessageSenderThread(String message,Socket socket){
        this.mSocket=socket;
        this.message=message;
    }

    @Override
    public void run()
    {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(mSocket.getOutputStream());
            dataOutputStream.writeUTF(message);
            Log.d(TAG,"Message sent");
        } catch (IOException e) {
            Log.d(TAG,e+"");
        }
    }
}
