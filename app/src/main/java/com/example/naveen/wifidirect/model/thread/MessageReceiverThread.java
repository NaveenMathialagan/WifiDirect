package com.example.naveen.wifidirect.model.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

import static com.example.naveen.wifidirect.utils.Constant.Registration.MESSAGE_KEY;
import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;


public class MessageReceiverThread extends Thread{
    private Socket mSocket;
    private Handler mHandler;
    public MessageReceiverThread(Socket socket, Handler handler)
    {
        this.mSocket=socket;
        this.mHandler=handler;
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                InputStream in=mSocket.getInputStream();
                DataInputStream dataInputStream=new DataInputStream(in);
                Log.d(TAG,"Message Received");
                Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putString(MESSAGE_KEY,dataInputStream.readUTF());
                message.setData(bundle);
                mHandler.sendMessage(message);
                Log.d(TAG,"Sent a message to handler");
            }
        }
        catch (Exception e)
        {
          Log.d(TAG,e+"");
        }
    }
}
