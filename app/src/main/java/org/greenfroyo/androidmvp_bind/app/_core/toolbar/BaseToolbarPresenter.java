package org.greenfroyo.androidmvp_bind.app._core.toolbar;

import android.app.Activity;
import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app.login.LoginDialog;
import org.greenfroyo.androidmvp_bind.provider.manager.DeviceInfoManager;
import org.greenfroyo.androidmvp_bind.provider.log.LogProvider;
import org.greenfroyo.androidmvp_bind.provider.user.UserProvider;

import java.util.Locale;

/**
 * Created by fchristysen on 7/20/16.
 */

public abstract class BaseToolbarPresenter<VM extends BaseToolbarViewModel>
        extends BasePresenter<VM>{

    private UserProvider mUserProvider;
    private LogProvider mLogProvider;
    private DeviceInfoManager mDeviceInfoManager;

    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        mUserProvider = UserProvider.get();
        getViewModel().setLogin(mUserProvider.isLogin());
        mUserProvider.isLoginStream().subscribe(next -> {
            getViewModel().setLogin(next);
        });
        mLogProvider = new LogProvider();
        mDeviceInfoManager = new DeviceInfoManager();
        mDeviceInfoManager.getLocaleStream().subscribe(next -> {
            getViewModel().setToastMessage(this.getClass().getSimpleName() + " > Locale has been changed");
        });
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

    public void switchLocale(){
        Locale locale = mDeviceInfoManager.getLocale();
        String lang;
        if(locale.getLanguage().equals("en")){
            lang = "id";
        }else{
            lang = "en";
        }
        mDeviceInfoManager.setLocale(new Locale(lang, locale.getCountry()));
    }

    public void openLoginDialog(){
        getViewModel().setOpenLoginDialog(true);
    }

    public void signOut(){
        mUserProvider.logout();
        getViewModel().setToastMessage(R.string.logout_success);
    }
}
