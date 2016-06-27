package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.lang.TransientField;
import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fchristysen on 6/7/16.
 *
 * This class expands the number of OnPropertyChangeCallbacks
 */

@Parcel
public abstract class BaseViewModel extends BaseObservable implements MvpViewModel {
    private String mToastMessage;

    private OnPropertyChangedCallback mOnPropertyChangedCallback;
    private List<OnPropertyChangedCallback> mOnPropertyChangedCallbackList;

    public BaseViewModel(){
        mOnPropertyChangedCallback = new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                for (OnPropertyChangedCallback callback:mOnPropertyChangedCallbackList) {
                    callback.onPropertyChanged(observable, i);
                }
            }
        };
        mOnPropertyChangedCallbackList = new ArrayList<>();
    }

    /** This methods is used to subscribes the main callbacks to the BaseObservable
     *  There's no need to call this method manually, as it is called through BasePresenter
     *  see @addOnPropertyChangeCallback
     */
    protected final void attachOnPropertyChangeCallback(){
        super.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    /** This methods is used to un-subscribes the main callbacks from the BaseObservable
     *  There's no need to call this method manually, as it is called through BasePresenter
     */
    protected final void detachOnPropertyChangeCallback(){
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

    @Bindable
    public String getToastMessage(){
        String r = mToastMessage;
        mToastMessage = null;
        return r;
    }

    public void setToastMessage(String toastMessage) {
        mToastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public boolean needToShowToast(){
        return mToastMessage != null;
    }
}
