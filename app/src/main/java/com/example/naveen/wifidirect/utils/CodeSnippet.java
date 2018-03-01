package com.example.naveen.wifidirect.utils;


import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.AVAILABLE;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.CONNECTED;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.FAILED;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.INVITED;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.UNAVAILABLE;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.available;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.connected;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.failed;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.invited;
import static com.example.naveen.wifidirect.utils.Constant.ConnectionStatus.unavailable;

public class CodeSnippet {

    private Context mContext;
    public CodeSnippet(Context context){
        this.mContext=context;
    }
    public static <T> int getListSize(List<T> list) {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }
    public String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        return df.format(c.getTime());
    }

    public String getStatus(int status){
        switch (status){
            case AVAILABLE:
                return available;
            case CONNECTED:
                return connected;
            case FAILED:
                return failed;
            case INVITED:
                return invited;
            case UNAVAILABLE:
                return unavailable;
        }
        return null;
    }
}
