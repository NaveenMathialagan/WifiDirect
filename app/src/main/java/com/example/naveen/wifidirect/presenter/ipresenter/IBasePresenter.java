package com.example.naveen.wifidirect.presenter.ipresenter;


import android.content.Intent;
import android.os.Bundle;

public interface IBasePresenter {
    void onCreatePresenter(Bundle bundle);

    void onStartPresenter();

    void onStopPresenter();

    void onPausePresenter();

    void onResumePresenter();

    void onDestroyPresenter();

    void onBackPressedPresenter();

    void onActivityResultPresenter(int requestCode, int resultCode, Intent data);
}
