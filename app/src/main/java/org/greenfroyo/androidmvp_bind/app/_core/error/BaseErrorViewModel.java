package org.greenfroyo.androidmvp_bind.app._core.error;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;

/**
 * Created by fchristysen on 7/20/16.
 */

public class BaseErrorViewModel extends BaseToolbarViewModel {
    public static final int ERROR_300 = 300;
    public static final int ERROR_400 = 400;
    public static final int ERROR_404 = 404;
    public static final int ERROR_500 = 500;

    protected boolean mIsError;
    protected int mErrorCode;

    protected String mButtonText;

    public void showError() {
        mIsError = true;
        notifyPropertyChanged(BR.error);
    }

    public void hideError() {
        mIsError = false;
        notifyPropertyChanged(BR.error);
    }

    @Bindable
    public boolean isError() {
        return mIsError;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
        notifyPropertyChanged(BR.error);
        notifyPropertyChanged(BR.errorTitle);
        notifyPropertyChanged(BR.errorDescription);
    }

    @Bindable
    public String getErrorTitle() {
        switch (mErrorCode) {
            case ERROR_300:
                return MVPBApp.resources().getString(R.string.error_300_title);
            case ERROR_400:
                return MVPBApp.resources().getString(R.string.error_400_title);
            case ERROR_404:
                return MVPBApp.resources().getString(R.string.error_404_title);
            case ERROR_500:
                return MVPBApp.resources().getString(R.string.error_500_title);
        }
        return "";
    }

    @Bindable
    public String getErrorDescription() {
        switch (mErrorCode) {
            case ERROR_300:
                return MVPBApp.resources().getString(R.string.error_300_desc);
            case ERROR_400:
                return MVPBApp.resources().getString(R.string.error_400_desc);
            case ERROR_404:
                return MVPBApp.resources().getString(R.string.error_404_desc);
            case ERROR_500:
                return MVPBApp.resources().getString(R.string.error_500_desc);
        }
        return "";
    }

    @Bindable
    public String getErrorButtonText() {
        return mButtonText != null ? mButtonText : MVPBApp.resources().getString(R.string.action_retry);
    }

    public void setErrorButtonText(String buttonText) {
        mButtonText = buttonText;
        notifyPropertyChanged(BR.errorButtonText);
    }
}
