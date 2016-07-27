package org.greenfroyo.androidmvp_bind.app._core.toolbar;

import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.provider.log.LogProvider;

/**
 * Created by fchristysen on 7/20/16.
 */

public abstract class BaseToolbarPresenter<VM extends BaseToolbarViewModel> extends BasePresenter<VM> {

    private LogProvider mLogProvider;

    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        mLogProvider = new LogProvider(MVPBApp.context());
    }

    public void showActivityCount(){
        getViewModel().setToastMessage("Current activity count is " + mLogProvider.getActivityCount()
                + "\n and stored count is " + mLogProvider.getStoredActivityCount());
    }

    public void increaseActivityCount(){
        mLogProvider.setActivityCount(mLogProvider.getActivityCount()+1);
        mLogProvider.setStoredActivityCount(mLogProvider.getStoredActivityCount()+1);
    }

    public void decreaseActivityCount(){
        mLogProvider.setActivityCount(mLogProvider.getActivityCount()-1);
        mLogProvider.setStoredActivityCount(mLogProvider.getStoredActivityCount()-1);
    }

    public void resetActivityCount(){
        mLogProvider.setActivityCount(1);
        mLogProvider.setStoredActivityCount(1);
    }
}
