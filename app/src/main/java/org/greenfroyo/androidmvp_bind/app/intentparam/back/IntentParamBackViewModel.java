package org.greenfroyo.androidmvp_bind.app.intentparam.back;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/7/16.
 */

@Parcel
public class IntentParamBackViewModel extends BaseToolbarViewModel {
    private int mValue;

    @Bindable
    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

}
