package org.greenfroyo.androidmvp_bind.widget.numberpicker;

import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.parceler.Parcels;

/**
 * Created by fchristysen on 6/28/16.
 */

public class NumberPickerPresenter extends BasePresenter<NumberPickerViewModel> {
    @Override
    public NumberPickerViewModel onInitViewModel() {
        return new NumberPickerViewModel();
    }

    public void substractValue(){
        getViewModel().substractValue();
    }

    public void addValue(){
        getViewModel().addValue();
    }
}
