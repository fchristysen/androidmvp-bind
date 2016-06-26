package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/7/16.
 */

@Parcel
public abstract class BaseViewModel extends BaseObservable implements MvpViewModel {
    private String mToastMessage;
    private String mSnackbarMessage;

    @Bindable
    public String getToastMessage() {
        return mToastMessage;
    }

    public void setToastMessage(String toastMessage) {
        mToastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void clearToastMessage(){
        mToastMessage = "";
    }

    @Bindable
    public String getSnackbarMessage() {
        return mSnackbarMessage;
    }

    public void setSnackbarMessage(String snackbarMessage) {
        mSnackbarMessage = snackbarMessage;
        notifyPropertyChanged(BR.snackbarMessage);
    }

    public void clearSnackbarMessage(){
        mSnackbarMessage = "";
    }
}
