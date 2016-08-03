package org.greenfroyo.androidmvp_bind.app.login;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;

/**
 * Created by fchristysen on 6/29/16.
 */

public class LoginViewModel extends BaseToolbarViewModel {

    public static final int STATE_DEFAULT = 0;
    public static final int STATE_LOGIN = 1;
    public static final int STATE_LOGGEDIN = 2;

    private int mState;
    private String mUsername;
    private String mUsernameError;
    private String mPassword;
    private String mPasswordError;

    public LoginViewModel(){
        super();
        this.mUsername = "";
        this.mUsernameError = null;
        this.mPassword = "";
        this.mPasswordError = null;
        this.mState = 0;
    }

    @Bindable
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getUsernameError() {
        return mUsernameError;
    }

    public void setUsernameError(String usernameError) {
        mUsernameError = usernameError;
        notifyPropertyChanged(BR.usernameError);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getPasswordError() {
        return mPasswordError;
    }

    public void setPasswordError(String passwordError) {
        mPasswordError = passwordError;
        notifyPropertyChanged(BR.passwordError);
    }

    @Bindable
    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
        notifyPropertyChanged(BR.state);
    }
}
