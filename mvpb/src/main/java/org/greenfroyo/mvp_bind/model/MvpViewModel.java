package org.greenfroyo.mvp_bind.model;

import android.databinding.Observable;

/**
 * Created by fchristysen on 5/20/16.
 */
public interface MvpViewModel{
    /**
     * This method will be called when the view model is attached to its presenter (presenter onCreate)
     */
    void onAttached();
    /**
     * This method will be called when the view model is detached to its presenter (presenter onDestroy)
     */
    void onDetached();

    void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback);

    void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback);
}
