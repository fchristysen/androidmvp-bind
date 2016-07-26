package org.greenfroyo.androidmvp_bind.app.intentparam.front;

import android.databinding.ObservableField;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/7/16.
 */

@Parcel
public class IntentParamFrontViewModel extends BaseToolbarViewModel {
    public ObservableField<Integer> mValue;

    public IntentParamFrontViewModel(){
        mValue = new ObservableField<>(0);
    }

    public void incrementValue(){
        mValue.set(mValue.get()+1);
    }
}
