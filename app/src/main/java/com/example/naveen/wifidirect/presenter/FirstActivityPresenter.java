package com.example.naveen.wifidirect.presenter;
import android.content.Intent;
import android.os.Bundle;
import com.example.naveen.wifidirect.presenter.ipresenter.IFirstActivityPresenter;
import com.example.naveen.wifidirect.view.iview.IFirstActivityView;



public class FirstActivityPresenter extends BasePresenter<IFirstActivityView> implements IFirstActivityPresenter {


    public FirstActivityPresenter(IFirstActivityView iView) {
        super(iView);
    }


    @Override
    public void onCreatePresenter(Bundle bundle) {

    }

    @Override
    public void onBackPressedPresenter() {

    }

    @Override
    public void onActivityResultPresenter(int requestCode, int resultCode, Intent data) {

    }
}
