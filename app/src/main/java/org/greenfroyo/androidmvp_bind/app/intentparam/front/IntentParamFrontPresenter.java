package org.greenfroyo.androidmvp_bind.app.intentparam.front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;
import org.greenfroyo.androidmvp_bind.app.intentparam.back.Henson;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamFrontPresenter extends BaseToolbarPresenter<IntentParamFrontViewModel> {

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
    }

    @Override
    public IntentParamFrontViewModel onCreateViewModel() {
        IntentParamFrontViewModel model = new IntentParamFrontViewModel();
        return model;
    }

    //region actionable
    public void onIncrementValue(){
        getViewModel().incrementValue();
    }

    public void openIntentParamBack(Context context){
        Intent intent = Henson.with(context)
                .gotoIntentParamBackActivity()
                .mValue(getViewModel().getValue())
                .build();
        context.startActivity(intent);
    }
    //endregion
}
