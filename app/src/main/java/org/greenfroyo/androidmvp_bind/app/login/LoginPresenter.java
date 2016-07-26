package org.greenfroyo.androidmvp_bind.app.login;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.App;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;
import org.greenfroyo.androidmvp_bind.domain.UserLogin;
import org.greenfroyo.androidmvp_bind.provider.user.UserProvider;

import rx.functions.Action1;

/**
 * Created by fchristysen on 6/29/16.
 */

public class LoginPresenter extends BaseToolbarPresenter<LoginViewModel> {

    private UserProvider mUserProvider;

    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        mUserProvider = new UserProvider(App.context());
    }

    @Override
    public LoginViewModel onInitViewModel() {
        return new LoginViewModel();
    }

    public void onLogin(){
        int usernameValidity = UserLogin.checkUsername(getViewModel().getUsername());
        switch (usernameValidity){
            case UserLogin.ERROR_USERNAME_EMPTY:
                getViewModel().setUsernameError(App.resources().getString(R.string.login_field_empty));
                break;
            case UserLogin.ERROR_USERNAME_TOO_SHORT:
                getViewModel().setUsernameError(App.resources().getString(R.string.login_password_too_short, UserLogin.MIN_LENGTH_USERNAME));
                break;
            case UserLogin.ERROR_USERNAME_TOO_LONG:
                getViewModel().setUsernameError(App.resources().getString(R.string.login_password_too_long, UserLogin.MAX_LENGTH_USERNAME));
                break;
        }

        int passwordValidity = UserLogin.checkUsername(getViewModel().getPassword());
        switch (passwordValidity){
            case UserLogin.ERROR_PASSWORD_EMPTY:
                getViewModel().setPasswordError(App.resources().getString(R.string.login_field_empty));
                break;
            case UserLogin.ERROR_PASSWORD_TOO_SHORT:
                getViewModel().setPasswordError(App.resources().getString(R.string.login_password_too_short, UserLogin.MIN_LENGTH_PASSWORD));
                break;
            case UserLogin.ERROR_PASSWORD_TOO_LONG:
                getViewModel().setPasswordError(App.resources().getString(R.string.login_password_too_long, UserLogin.MAX_LENGTH_PASSWORD));
                break;
        }

        if(usernameValidity == UserLogin.VALID_USERNAME
                && passwordValidity == UserLogin.VALID_PASSWORD){
            Action1<Integer> onNext = next -> {
                String message = "";
                if(next == UserProvider.LOGIN_SUCCESS){
                    message = App.resources().getString(R.string.login_result);
                }else if(next == UserProvider.LOGIN_ERROR_NO_ACCOUNT){
                    message = App.resources().getString(R.string.login_no_account);
                }
                getViewModel().setToastMessage(message);
            };
            Action1<Throwable> onError = error -> {
                String message = null;
                if(error instanceof RuntimeException){
                    if(error.getCause() instanceof NetworkErrorException) {
                        message = App.resources().getString(R.string.login_no_network);
                    }
                }
                if(message == null){
                    message = App.resources().getString(R.string.common_unknown_error);
                }
                getViewModel().setToastMessage(message);
            };
            mUserProvider.login(getViewModel().getUsername(), getViewModel().getPassword()).subscribe(
                    onNext, onError
            );
        }
    }

    public void actionOpenLoadingDialog(Activity activity){
        LoginDialog dialog = new LoginDialog(activity);
        dialog.show();
    }
}
