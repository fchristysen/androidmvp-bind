package org.greenfroyo.androidmvp_bind.app.intentparam.front;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamFrontViewModel extends BaseViewModel{
    private int mValue;

    @Bindable
    public int getValue() {
        return mValue;
    }

    public void incrementValue(){
        mValue++;
        notifyPropertyChanged(BR.value);
    }
}
