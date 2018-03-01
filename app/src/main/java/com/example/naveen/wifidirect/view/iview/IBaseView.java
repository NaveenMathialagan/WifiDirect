package com.example.naveen.wifidirect.view.iview;

import android.support.v7.app.AppCompatActivity;

import com.example.naveen.wifidirect.utils.CodeSnippet;


public interface IBaseView {
    void showMessage(String message);
    AppCompatActivity getActivity();
    CodeSnippet getCodeSnippet();
}
