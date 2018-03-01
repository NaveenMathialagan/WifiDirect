package com.example.naveen.wifidirect.adapters.singleton;

import android.os.Parcel;
import android.os.Parcelable;

public class UserMessage implements Parcelable{
   private String message;
    private String time;
    private String type;

    public UserMessage(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected UserMessage(Parcel in) {
        message = in.readString();
        time=in.readString();
        type=in.readString();
    }

    public static final Creator<UserMessage> CREATOR = new Creator<UserMessage>() {
        @Override
        public UserMessage createFromParcel(Parcel in) {
            return new UserMessage(in);
        }

        @Override
        public UserMessage[] newArray(int size) {
            return new UserMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeString(time);
        parcel.writeString(type);
    }
}
