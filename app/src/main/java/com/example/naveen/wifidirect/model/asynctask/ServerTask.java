package com.example.naveen.wifidirect.model.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.naveen.wifidirect.presenter.ChatActivityPresenter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;

public class ServerTask extends AsyncTask<Void,Void,Socket> {

    private ChatActivityPresenter mChatActivityPresenter;

    public ServerTask(ChatActivityPresenter chatActivityPresenter){
        mChatActivityPresenter=chatActivityPresenter;
    }

    @Override
    protected Socket doInBackground(Void... params) {
        String text="";
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            Log.d(TAG,"Connected");
            Log.d(TAG,text);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Socket socket) {
        mChatActivityPresenter.setServerSocket(socket);
    }
}