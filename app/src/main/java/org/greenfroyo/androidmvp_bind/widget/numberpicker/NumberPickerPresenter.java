package org.greenfroyo.androidmvp_bind.widget.numberpicker;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;

/**
 * Created by fchristysen on 6/28/16.
 */

public class NumberPickerPresenter extends BasePresenter<NumberPickerViewModel> {
    @Override
    public NumberPickerViewModel onCreateViewModel() {
        return new NumberPickerViewModel();
    }

    public void setValue(int value) {
        getViewModel().setValue(value);
    }

    public void getValue() {
        getViewModel().getValue();
    }

    public void substractValue() {
        getViewModel().substractValue();
    }

    public void addValue() {
        getViewModel().addValue();
    }
}
