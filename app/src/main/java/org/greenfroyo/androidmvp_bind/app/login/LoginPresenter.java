package org.greenfroyo.androidmvp_bind.app.login;

import android.accounts.NetworkErrorException;
import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;
import org.greenfroyo.androidmvp_bind.bridge.UserBridge;
import org.greenfroyo.androidmvp_bind.domain.UserLogin;
import org.greenfroyo.androidmvp_bind.provider.user.UserLoginDataModel;
import org.greenfroyo.androidmvp_bind.provider.user.UserProvider;

import rx.functions.Action1;

import static org.greenfroyo.androidmvp_bind.domain.UserLogin.getPasswordErrorString;
import static org.greenfroyo.androidmvp_bind.domain.UserLogin.getUsernameErrorString;

/**
 * Created by fchristysen on 6/29/16.
 */

public class LoginPresenter extends BaseToolbarPresenter<LoginViewModel> {

    private UserProvider mUserProvider;

    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        mUserProvider = new UserProvider();
        if(mUserProvider.isLogin()) {
            getViewModel().setState(LoginViewModel.STATE_LOGGEDIN);
            UserBridge.loginViewModel(getViewModel(), mUserProvider.getUser());
        }else{
            getViewModel().setState(LoginViewModel.STATE_DEFAULT);
        }
        mUserProvider.isLoginStream().subscribe(next->{
            if(next) {
                getViewModel().setState(LoginViewModel.STATE_LOGGEDIN);
                UserBridge.loginViewModel(getViewModel(), mUserProvider.getUser());
            }else{
                getViewModel().setState(LoginViewModel.STATE_DEFAULT);
            }
        });
    }

    @Override
    public LoginViewModel onCreateViewModel() {
        return new LoginViewModel();
    }

    public void onLogin(){
        getViewModel().setState(LoginViewModel.STATE_LOGIN);
        int usernameValidity = UserLogin.checkUsername(getViewModel().getUsername());
        if(usernameValidity != UserLogin.VALID_USERNAME){
            getViewModel().setUsernameError(getUsernameErrorString(usernameValidity));
            getViewModel().setState(LoginViewModel.STATE_DEFAULT);
            return;
        }

        int passwordValidity = UserLogin.checkPassword(getViewModel().getPassword());
        if(passwordValidity != UserLogin.VALID_PASSWORD){
            getViewModel().setPasswordError(getPasswordErrorString(passwordValidity));
            getViewModel().setState(LoginViewModel.STATE_DEFAULT);
            return;
        }

        Action1<UserLoginDataModel> onNext = next -> {
            if(next.loginResult == UserProvider.LOGIN_SUCCESS){
                getViewModel().setToastMessage(MVPBApp.resources().getString(R.string.login_success));
                getViewModel().setPassword("");
                getViewModel().setState(LoginViewModel.STATE_LOGGEDIN);
            }else if(next.loginResult == UserProvider.LOGIN_ERROR_NO_ACCOUNT){
                getViewModel().setToastMessage(MVPBApp.resources().getString(R.string.login_no_account));
                getViewModel().setState(LoginViewModel.STATE_DEFAULT);
            }
        };
        Action1<Throwable> onError = error -> {
            String message = null;
            if(error.getCause() instanceof NetworkErrorException) {
                message = MVPBApp.resources().getString(R.string.login_no_network);
            }
            if(message == null){
                message = MVPBApp.resources().getString(R.string.common_unknown_error);
            }
            getViewModel().setToastMessage(message);
            getViewModel().setState(LoginViewModel.STATE_DEFAULT);
        };
        mUserProvider.login(getViewModel().getUsername(), getViewModel().getPassword()).subscribe(
                onNext, onError
        );
    }

    public void onLogout(){
        mUserProvider.logout();
        getViewModel().setToastMessage(MVPBApp.resources().getString(R.string.logout_success));
    }
}
