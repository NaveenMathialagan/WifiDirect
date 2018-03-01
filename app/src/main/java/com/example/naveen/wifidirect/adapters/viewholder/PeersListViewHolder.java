package com.example.naveen.wifidirect.adapters.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.listener.IPeersNameListListener;
import com.example.naveen.wifidirect.adapters.singleton.PeersDetails;

public class PeersListViewHolder extends BaseViewHolder<PeersDetails> implements View.OnClickListener{


    private TextView vTDeviceName,vTStatus;
    private IPeersNameListListener<PeersDetails> iPeersNameListListener;
    public PeersListViewHolder(View itemView, IPeersNameListListener<PeersDetails> iPeersNameListListener) {
        super(itemView);
        variableInitialization();
        this.iPeersNameListListener=iPeersNameListListener;
    }

    private void variableInitialization(){
        vTDeviceName=itemView.findViewById(R.id.text_device_name);
        vTStatus=itemView.findViewById(R.id.text_status);
        LinearLayout mlinearLayout = itemView.findViewById(R.id.layout_peer_content);
        mlinearLayout.setOnClickListener(this);
    }

    @Override
    protected void populateData(PeersDetails data) {
        if (data.getWifiP2pDevice()==null){
            String NO_DEVICE = "No Device Found";
            vTDeviceName.setText(NO_DEVICE);
        }else {
            vTDeviceName.setText(data.getWifiP2pDevice().deviceName);
        }
        vTStatus.setText(data.getStatus());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_peer_content:
                iPeersNameListListener.changeStatus(getAdapterPosition());
                break;
        }
    }

}
