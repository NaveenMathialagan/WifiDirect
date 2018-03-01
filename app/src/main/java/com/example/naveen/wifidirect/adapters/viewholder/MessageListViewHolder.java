package com.example.naveen.wifidirect.adapters.viewholder;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.naveen.wifidirect.R;
import com.example.naveen.wifidirect.adapters.listener.IMessageListListener;
import com.example.naveen.wifidirect.adapters.singleton.UserMessage;

import static com.example.naveen.wifidirect.utils.Constant.Registration.SEND;

public class MessageListViewHolder extends BaseViewHolder<UserMessage>{

    private TextView vTMessage,vTTime;
    private CardView vCard;
    private IMessageListListener<UserMessage> iMessageListListener;

    public MessageListViewHolder(View itemView, IMessageListListener<UserMessage> iMessageListListener) {
        super(itemView);
        this.iMessageListListener = iMessageListListener;
        variableInitialization();
    }

    private void variableInitialization(){
        vTMessage=itemView.findViewById(R.id.message_text);
        vCard=itemView.findViewById(R.id.card_view);
    }

    @Override
    protected void populateData(UserMessage userMessage) {
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (userMessage.getType().equals(SEND)){
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            params.setMargins(50,10,10,0);
            vCard.setLayoutParams(params);
        }else {
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            params.setMargins(10,5,50,0);
            vCard.setLayoutParams(params);

        }
        vTMessage.setText(userMessage.getMessage());
    }
}
