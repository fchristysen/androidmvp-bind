package org.greenfroyo.androidmvp_bind.bridge;

import org.greenfroyo.androidmvp_bind.app.login.LoginViewModel;
import org.greenfroyo.androidmvp_bind.provider.user.UserLoginDataModel;

/**
 * Created by fchristysen on 8/4/16.
 */

public class UserBridge {

    public static void loginViewModel(LoginViewModel viewModel, UserLoginDataModel.UserDataModel userDataModel){
        viewModel.setUsername(userDataModel.username);
        viewModel.setAvatar(userDataModel.avatarUrl);
        viewModel.setFullname(userDataModel.fullname);
    }
}
