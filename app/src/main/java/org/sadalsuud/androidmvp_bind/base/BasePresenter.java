package org.sadalsuud.androidmvp_bind.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.sadalsuud.androidmvp_bind.framework.presenter.Presenter;
import org.sadalsuud.androidmvp_bind.framework.view.MvpView;

import icepick.Icepick;


/**
 * Created by fchristysen on 1/28/16.
 * BasePresenter features :
 * # Icepick Support
 *      > Subclass doesn't have to manually save and restore isntance state
 *        just add @State annotation before variable declaration
 */
public abstract class BasePresenter<V extends MvpView> extends Presenter<V> {

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
        Icepick.restoreInstanceState(this, presenterState);
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
        Icepick.saveInstanceState(this, outPresenterState);
    }
}
