package org.greenfroyo.androidmvp_bind.app.intentparam;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamFrontViewModel extends BaseViewModel{
    private int mValue;

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }
}
