package org.greenfroyo.androidmvp_bind.app._core.toolbar;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 7/20/16.
 */

@Parcel
public class BaseToolbarViewModel extends BaseViewModel {
    protected boolean isOpenLoginDialog;
    protected boolean isLogin;

    @Bindable
    public boolean isOpenLoginDialog() {
        return isOpenLoginDialog;
    }

    public void setOpenLoginDialog(boolean openLoginDialog) {
        isOpenLoginDialog = openLoginDialog;
        notifyPropertyChanged(BR.openLoginDialog);
    }

    @Bindable
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
        notifyPropertyChanged(BR.login);
    }
}
