package org.greenfroyo.androidmvp_bind.app.compoundview;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/27/16.
 */

@Parcel
public class CompoundViewViewModel extends BaseToolbarViewModel {
    protected int num1;
    protected int num2;
    protected int num3;

    @Bindable
    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
        notifyPropertyChanged(BR.num1);
    }

    @Bindable
    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
        notifyPropertyChanged(BR.num2);
    }

    @Bindable
    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
        notifyPropertyChanged(BR.num3);
    }
}
