package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.mvpb.base.BaseMvpViewModel;

/**
 * Created by fchristysen on 6/7/16.
 * <p>
 * !IMPORTANT, subclass of this view model required to use @Parcel annotation for enabling
 * automatic save and restore of view model
 * This class expands the number of attachable OnPropertyChangeCallbacks
 */

public abstract class BaseViewModel extends BaseMvpViewModel {
    protected String mToastMessage;

    public BaseViewModel() {

    }

    @Bindable
    public String getToastMessage() {
        String r = mToastMessage;
        mToastMessage = null;
        return r;
    }

    public void setToastMessage(int toastMessage) {
        mToastMessage = MVPBApp.resources().getString(toastMessage);
        notifyPropertyChanged(BR.toastMessage);
    }

    public void setToastMessage(String toastMessage) {
        mToastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public boolean needToShowToast() {
        return mToastMessage != null;
    }
}
