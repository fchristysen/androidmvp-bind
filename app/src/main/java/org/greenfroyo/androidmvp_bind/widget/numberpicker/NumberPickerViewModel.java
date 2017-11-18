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
    int mFactorial;
    int mExpon;

    @Bindable
    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
        notifyPropertyChanged(BR.value);
    }

    public void substractValue() {
        mValue--;
        notifyPropertyChanged(BR.value);
    }

    public void addValue() {
        mValue++;
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public int getFactorial() {
        return mFactorial;
    }

    public void setFactorial(int factorial) {
        mFactorial = factorial;
        notifyPropertyChanged(BR.factorial);
    }

    @Bindable
    public int getExpon() {
        return mExpon;
    }

    public void setExpon(int expon) {
        mExpon = expon;
        notifyPropertyChanged(BR.expon);
    }
}
