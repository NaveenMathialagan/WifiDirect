package com.example.naveen.wifidirect.presenter;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.naveen.wifidirect.adapters.MessageListAdapter;
import com.example.naveen.wifidirect.adapters.listener.IMessageListListener;
import com.example.naveen.wifidirect.adapters.singleton.UserMessage;
import com.example.naveen.wifidirect.listener.SocketObjectListener;
import com.example.naveen.wifidirect.model.asynctask.ClientTask;
import com.example.naveen.wifidirect.model.asynctask.ServerTask;
import com.example.naveen.wifidirect.model.singleton.ConnectionInfo;
import com.example.naveen.wifidirect.model.thread.MessageReceiverThread;
import com.example.naveen.wifidirect.model.thread.MessageSenderThread;
import com.example.naveen.wifidirect.presenter.ipresenter.IChatActivityPresenter;
import com.example.naveen.wifidirect.view.iview.IChatActivityView;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import static com.example.naveen.wifidirect.utils.Constant.Registration.BUNDLE_KEY;
import static com.example.naveen.wifidirect.utils.Constant.Registration.CONNECTION_INFO_KEY;
import static com.example.naveen.wifidirect.utils.Constant.Registration.MESSAGE_KEY;
import static com.example.naveen.wifidirect.utils.Constant.Registration.RECEIVE;
import static com.example.naveen.wifidirect.utils.Constant.Registration.SEND;
import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;

public class ChatActivityPresenter extends BasePresenter<IChatActivityView> implements IChatActivityPresenter,SocketObjectListener{

    private List<UserMessage> mUserMessageList;
    private MessageListAdapter mMessageListAdapter;
    private Socket mSocket;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Log.d(TAG,"Came to handler");
            final Bundle bundle=msg.getData();
            Log.d(TAG,bundle.getString(MESSAGE_KEY));
            iView.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateMessage(bundle.getString(MESSAGE_KEY));
                }
            });
        }
    };

    private IMessageListListener<UserMessage> iMessageListListener=new IMessageListListener<UserMessage>() {
        @Override
        public void onClickItem(UserMessage data) {

        }
        @Override
        public void closeItem(int pos) {

        }
    };
    public ChatActivityPresenter(IChatActivityView iView) {
        super(iView);
        mUserMessageList=new ArrayList<>();
        setUserMessageListAdapter();
    }

    @Override
    public void onCreatePresenter(Bundle b) {
        Bundle bundle=iView.getActivity().getIntent().getBundleExtra(BUNDLE_KEY);
        ConnectionInfo connectionInfo=bundle.getParcelable(CONNECTION_INFO_KEY);
        assert connectionInfo != null;
        if (connectionInfo.isOwner()){
            iView.setConnectionType();
            new ServerTask(this).execute();
        }else {
            new ClientTask(this,connectionInfo.getIpAddress()).execute();
        }
    }

    @Override
    public void onBackPressedPresenter() {

    }

    @Override
    public void onActivityResultPresenter(int requestCode, int resultCode, Intent data) {

    }

    private void setUserMessageListAdapter(){
        mMessageListAdapter=new MessageListAdapter(mUserMessageList,iMessageListListener);
        iView.setMessageListAdapter(mMessageListAdapter);
    }
    @Override
    public void sendMessage(String message) {
        UserMessage userMessage=new UserMessage();
        userMessage.setMessage(message);
        userMessage.setTime(iView.getCodeSnippet().getCurrentTime());
        userMessage.setType(SEND);
        mMessageListAdapter.addItem(userMessage);
        iView.scrollToLast(mMessageListAdapter.getItemCount()-1);
        MessageSenderThread messageSenderThread=new MessageSenderThread(message,mSocket);
        messageSenderThread.start();
    }

    @Override
    public void setServerSocket(Socket socket) {
        if (socket!=null) {
            mSocket = socket;
            MessageReceiverThread messageReceiverThread=new MessageReceiverThread(mSocket,mHandler);
            messageReceiverThread.start();
            Log.d(TAG, "set Server Socket");
        }
    }

    @Override
    public void setClientSocket(Socket socket) {
        if (socket != null) {
            mSocket = socket;
            MessageReceiverThread messageReceiverThread = new MessageReceiverThread(mSocket, mHandler);
            messageReceiverThread.start();
            Log.d(TAG, "set Client Socket");
        }
    }

    private void updateMessage(String message) {
        UserMessage userMessage=new UserMessage();
        userMessage.setMessage(message);
        userMessage.setTime(iView.getCodeSnippet().getCurrentTime());
        userMessage.setType(RECEIVE);
        mMessageListAdapter.addItem(userMessage);
        iView.scrollToLast(mMessageListAdapter.getItemCount()-1);
    }


    @Override
    public void onStopPresenter() {

    }

    @Override
    public void onDestroyPresenter() {
        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
