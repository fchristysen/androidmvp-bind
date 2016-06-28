package org.greenfroyo.androidmvp_bind.app.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

/**
 * Created by fchristysen on 6/21/16.
 */

@Parcel
public class HomeItemViewModel extends BaseObservable{
    @ParcelProperty("class")
    private Class mClass;

    @ParcelConstructor
    public HomeItemViewModel(@ParcelProperty("class") Class pageClass) {
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
