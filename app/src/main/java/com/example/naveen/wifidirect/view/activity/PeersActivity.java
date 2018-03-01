package com.example.naveen.wifidirect.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.PeersListAdapter;
import com.example.naveen.wifidirect.model.singleton.ConnectionInfo;
import com.example.naveen.wifidirect.presenter.PeersActivityPresenter;
import com.example.naveen.wifidirect.presenter.ipresenter.IPeersActivityPresenter;
import com.example.naveen.wifidirect.view.iview.IPeersActivityView;

import static com.example.naveen.wifidirect.utils.Constant.Registration.BUNDLE_KEY;
import static com.example.naveen.wifidirect.utils.Constant.Registration.CONNECTION_INFO_KEY;

public class PeersActivity extends BaseActivity<IPeersActivityPresenter> implements IPeersActivityView,View.OnClickListener {


    private RecyclerView mRecyclerView;

    @NonNull
    @Override
    IPeersActivityPresenter bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_peers);
        variableInitialization();
        return new PeersActivityPresenter(this);
    }

    private void variableInitialization(){
        Button buttonDiscover=findViewById(R.id.button_discover);
        buttonDiscover.setOnClickListener(this);
        mRecyclerView=findViewById(R.id.peers_recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLinearLayoutManager.setStackFromEnd(false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_discover:
                iPresenter.discoverPeers();
                break;
        }
    }

    @Override
    public void setPeersListAdapter(PeersListAdapter peersListAdapter) {
        mRecyclerView.setAdapter(peersListAdapter);
    }

    @Override
    public void navigateToChatActivity(boolean isOwner, String ip) {
        Bundle bundle=new Bundle();
        ConnectionInfo connectionInfo=new ConnectionInfo();
        connectionInfo.setOwner(isOwner);
        connectionInfo.setIpAddress(ip);
        bundle.putParcelable(CONNECTION_INFO_KEY,connectionInfo);
        Intent intent=new Intent(this,ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(BUNDLE_KEY,bundle);
        startActivity(intent);
        finish();
    }
}
