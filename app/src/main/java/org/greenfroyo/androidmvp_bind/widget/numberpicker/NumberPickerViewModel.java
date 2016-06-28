package org.greenfroyo.androidmvp_bind.widget.numberpicker;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/28/16.
 */

@Parcel
public class NumberPickerViewModel extends BaseViewModel {
    int mValue;

    @Bindable
    public int getValue() {
        return mValue;
    }

    public void substractValue() {
        mValue--;
        notifyPropertyChanged(BR.value);
    }

    public void addValue() {
        mValue++;
        notifyPropertyChanged(BR.value);
    }
}
