package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.BaseObservable;

import org.greenfroyo.androidmvp_bind.lang.TransientField;
import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/7/16.
 */

@Parcel
public abstract class BaseViewModel extends BaseObservable implements MvpViewModel {
    private final TransientField<String> mToastMessage = new TransientField<>();
    private final TransientField<String> mSnackbarMessage = new TransientField<>();

    public String getToastMessage() {
        return mToastMessage.get();
    }

    public void setToastMessage(String toastMessage) {
        mToastMessage.set(toastMessage);
    }

    public boolean needToShowToast(){
        return mToastMessage.containValue();
    }

    public String getSnackbarMessage() {
        return mSnackbarMessage.get();
    }

    public void setSnackbarMessage(String snackbarMessage) {
        mSnackbarMessage.set(snackbarMessage);
    }

    public boolean needToShowSnackbar(){
        return mSnackbarMessage.containValue();
    }


}
