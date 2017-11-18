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
        recalculate();
    }

    public void substractValue() {
        getViewModel().substractValue();
        recalculate();
    }

    public void addValue() {
        getViewModel().addValue();
        recalculate();
    }

    public void recalculate(){
        int value = getViewModel().getValue();

        getViewModel().setFactorial(factorial(value));
        getViewModel().setExpon((int)Math.pow(value, 2));
    }

    public static int factorial(int number){
        if(number > 0){
            return number * factorial(number-1);
        }
        return 1;
    }
}
