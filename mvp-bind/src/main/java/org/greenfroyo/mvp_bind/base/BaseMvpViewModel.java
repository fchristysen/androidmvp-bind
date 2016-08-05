package org.greenfroyo.mvp_bind.base;

import android.databinding.BaseObservable;
import android.databinding.Observable;


import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fchristysen on 7/17/16.
 */

@Parcel
public class BaseMvpViewModel extends BaseObservable implements MvpViewModel {

    @Transient
    private OnPropertyChangedCallback mOnPropertyChangedCallback;
    @Transient
    private List<OnPropertyChangedCallback> mOnPropertyChangedCallbackList;

    public BaseMvpViewModel() {
        mOnPropertyChangedCallback = new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                for (OnPropertyChangedCallback callback : mOnPropertyChangedCallbackList) {
                    callback.onPropertyChanged(observable, i);
                }
            }
        };
        mOnPropertyChangedCallbackList = new ArrayList<>();
    }

    /**
     * This methods is used to subscribes the main callbacks to the BaseObservable
     * There's no need to call this method manually, as it is called through BasePresenter
     * see @addOnPropertyChangeCallback
     */
    protected final void attachOnPropertyChangeCallback() {
        super.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    /**
     * This methods is used to un-subscribes the main callbacks from the BaseObservable
     * There's no need to call this method manually, as it is called through BasePresenter
     */
    protected final void detachOnPropertyChangeCallback() {
        super.removeOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    @Override
    public final synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mOnPropertyChangedCallbackList.add(callback);
    }

    @Override
    public final synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        mOnPropertyChangedCallbackList.remove(callback);
    }
}
