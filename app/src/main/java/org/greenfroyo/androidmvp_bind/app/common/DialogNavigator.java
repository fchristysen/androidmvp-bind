package org.greenfroyo.androidmvp_bind.app.common;

import android.app.Activity;

import org.greenfroyo.androidmvp_bind.app._core.BaseDialog;
import org.greenfroyo.androidmvp_bind.app.login.LoginDialog;

/**
 * Created by fchristysen on 8/4/16.
 */

public class DialogNavigator {

    public static BaseDialog gotoLoginDialog(Activity owner) {
        LoginDialog dialog = new LoginDialog(owner);
        return dialog;
    }
}
