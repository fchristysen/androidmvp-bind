package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.Bindable;
import android.databinding.Observable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.mvpb.base.BaseMvpViewModel;
import org.greenfroyo.mvpb.view.MainPropertyChangeCallback;
import org.parceler.Transient;

import java.util.HashSet;

/**
 * Created by fchristysen on 6/7/16.
 * <p>
 * !IMPORTANT, subclass of this view model required to use @Parcel annotation for enabling
 * automatic save and restore of view model
 * This class expands the number of attachable OnPropertyChangeCallbacks
 */

public abstract class BaseViewModel extends BaseMvpViewModel {
    protected String mToastMessage;
    @Transient protected OnPropertyChangedCallback mPendingPropertyChangeListener;
    @Transient protected HashSet<Integer> mPendings = new HashSet<>();
    @Transient protected boolean isPending = true;

    public BaseViewModel() {
        mPendingPropertyChangeListener = new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                synchronized (BaseViewModel.this) {
                    if (isPending) {
                        mPendings.add(i);
                    }
                }
            }
        };
        this.addOnPropertyChangedCallback(mPendingPropertyChangeListener);
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

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        super.addOnPropertyChangedCallback(callback);
        if(callback instanceof MainPropertyChangeCallback) {
            isPending = false;
        }
        synchronized (BaseViewModel.this) {
            for (Integer i : mPendings) {
                callback.onPropertyChanged(this, i);
            }
            mPendings.clear();
        }
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        super.removeOnPropertyChangedCallback(callback);
        if(callback instanceof MainPropertyChangeCallback) {
            isPending = true;
        }
    }


}
