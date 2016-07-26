package org.greenfroyo.androidmvp_bind.app.intentparam.front;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/7/16.
 * Observable field does not have Parcel annotations, therefore saveState of these field won't work
 */

@Parcel
public class IntentParamFrontViewModel extends BaseToolbarViewModel {
    protected int mValue;

    public IntentParamFrontViewModel() {
        mValue = 0;
    }

    public void incrementValue() {
        mValue++;
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public int getValue() {
        return mValue;
    }
}
