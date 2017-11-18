package org.greenfroyo.androidmvp_bind.app._core.error;

import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;

/**
 * Created by fchristysen on 7/20/16.
 */

public abstract class BaseErrorPresenter<VM extends BaseErrorViewModel> extends BaseToolbarPresenter<VM> {

    @Override
    protected void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
    }

    public void showError(int errorCode) {
        getViewModel().setErrorCode(errorCode);
        getViewModel().showError();
    }

    public void hideError() {
        getViewModel().hideError();
    }
}
