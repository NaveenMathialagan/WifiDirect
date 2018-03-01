package com.example.naveen.wifidirect.view.activity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.MessageListAdapter;
import com.example.naveen.wifidirect.presenter.ChatActivityPresenter;
import com.example.naveen.wifidirect.presenter.ipresenter.IChatActivityPresenter;
import com.example.naveen.wifidirect.view.iview.IChatActivityView;

import static com.example.naveen.wifidirect.utils.Constant.Registration.EMPTY;
import static com.example.naveen.wifidirect.utils.Constant.Registration.TAG;

public class ChatActivity extends BaseActivity<IChatActivityPresenter> implements IChatActivityView,View.OnClickListener {

    private EditText eMessage;
    private RecyclerView mRecyclerView;
    private View vConnectionType;
    @NonNull
    @Override
    IChatActivityPresenter bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_chat);
        variableInitialization();
        return new ChatActivityPresenter(this);
    }

    private void variableInitialization(){
        vConnectionType=findViewById(R.id.connection_type);
        eMessage=findViewById(R.id.edit_message);
        ImageView vISend = findViewById(R.id.buttton_send);
        vISend.setOnClickListener(this);
        mRecyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLinearLayoutManager.setStackFromEnd(false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttton_send:
                validate();
                break;
        }
    }
    private void validate(){
        String message=eMessage.getText().toString();
        Log.d(TAG,message);
        if (!message.isEmpty()){
            eMessage.setText(EMPTY);
            iPresenter.sendMessage(message);
        }else {
            showMessage(getString(R.string.enter_message));
        }
    }

    @Override
    public void setMessageListAdapter(MessageListAdapter messageListAdapter) {
        mRecyclerView.setAdapter(messageListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.peers_setting:
                navigateToPeersActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void navigateToPeersActivity(){
        Intent intent=new Intent(this,PeersActivity.class);
        startActivity(intent);
    }

    @Override
    public void setConnectionType() {
        GradientDrawable bgShape = (GradientDrawable) vConnectionType.getBackground();
        bgShape.setColor(ContextCompat.getColor(getApplicationContext(), R.color.connection_type));
    }

    @Override
    public void scrollToLast(int position) {
        mRecyclerView.scrollToPosition(position);
    }
}
