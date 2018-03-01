package com.example.naveen.wifidirect.model.asynctask;


import android.os.AsyncTask;
import android.util.Log;

import com.example.naveen.wifidirect.presenter.ChatActivityPresenter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;

public class ClientTask extends AsyncTask<Void, Void, Socket> {

    private String ipAddress;
    private ChatActivityPresenter mChatActivityPresenter;

    public ClientTask(ChatActivityPresenter chatActivityPresenter,String ipAddress){
        this.ipAddress=ipAddress;
        this.mChatActivityPresenter=chatActivityPresenter;
    }

    @Override
    protected Socket doInBackground(Void... voids) {
        Socket mSocket = new Socket();
        try {
            mSocket.bind(null);
            mSocket.connect((new InetSocketAddress(ipAddress, 8888)), 500);
            Log.d(TAG,"Connected");
            return mSocket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Socket socket) {
        mChatActivityPresenter.setClientSocket(socket);
    }
}
