package org.greenfroyo.mvpb.base;

import android.databinding.BaseObservable;
import android.databinding.Observable;

import org.greenfroyo.mvpb.model.MvpViewModel;
import org.greenfroyo.mvpb.view.MainPropertyChangeCallback;
import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.HashSet;

/**
 * Created by fchristysen on 7/17/16.
 */

@Parcel
public class BaseMvpViewModel extends BaseObservable implements MvpViewModel {

    @Transient protected OnPropertyChangedCallback mPendingPropertyChangeListener;
    @Transient protected HashSet<Integer> mPendings = new HashSet<>();
    @Transient protected boolean isPending = true;

    public BaseMvpViewModel() {
        mPendingPropertyChangeListener = new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                synchronized (BaseMvpViewModel.this) {
                    if (isPending) {
                        mPendings.add(i);
                    }
                }
            }
        };
        this.addOnPropertyChangedCallback(mPendingPropertyChangeListener);
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        super.addOnPropertyChangedCallback(callback);
        if(callback instanceof MainPropertyChangeCallback) {
            isPending = false;
        }
        synchronized (BaseMvpViewModel.this) {
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
