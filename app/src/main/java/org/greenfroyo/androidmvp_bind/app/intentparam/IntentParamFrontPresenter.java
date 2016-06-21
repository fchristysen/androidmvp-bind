package org.greenfroyo.androidmvp_bind.app.intentparam;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamFrontPresenter extends BasePresenter<IntentParamFrontViewModel>{

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
    }

    @Override
    public IntentParamFrontViewModel onInitViewModel() {
        IntentParamFrontViewModel model = new IntentParamFrontViewModel();
        model.setValue(0);
        return model;
    }

    public void onIncrementValue(){
        getViewModel().setValue(getViewModel().getValue()+1);
    }
}
