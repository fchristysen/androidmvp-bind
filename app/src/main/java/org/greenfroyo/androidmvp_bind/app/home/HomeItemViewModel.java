package org.greenfroyo.androidmvp_bind.app.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;

/**
 * Created by fchristysen on 6/21/16.
 */

public class HomeItemViewModel extends BaseObservable{
    private Class mClass;

    public HomeItemViewModel(Class pageClass) {
        mClass = pageClass;
    }

    public Class getClassObject() {
        return mClass;
    }

    public void setClass(Class aClass) {
        mClass = aClass;
        notifyPropertyChanged(BR.text);
    }

    @Bindable
    public String getText() {
        return mClass.getSimpleName();
    }
}
