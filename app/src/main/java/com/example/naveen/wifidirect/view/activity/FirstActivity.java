package com.example.naveen.wifidirect.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.presenter.FirstActivityPresenter;
import com.example.naveen.wifidirect.presenter.ipresenter.IFirstActivityPresenter;
import com.example.naveen.wifidirect.view.iview.IFirstActivityView;

public class FirstActivity extends BaseActivity<IFirstActivityPresenter> implements IFirstActivityView,View.OnClickListener {

    @NonNull
    @Override
    IFirstActivityPresenter bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_first);
        variableInitialization();
        return new FirstActivityPresenter(this);
    }

    private void variableInitialization() {
        Button buttonConnect = findViewById(R.id.button_connect);
        buttonConnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_connect:
                navigateToPeersActivity();
                break;
        }
    }

    private void navigateToPeersActivity(){
        Intent intent=new Intent(this,PeersActivity.class);
        startActivity(intent);
        finish();
    }

    /*private void navigateToChatActivity(){
        Intent intent=new Intent(this,ChatActivity.class);
        startActivity(intent);
    }*/
}
