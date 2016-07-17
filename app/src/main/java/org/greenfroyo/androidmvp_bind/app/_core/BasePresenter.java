package org.greenfroyo.androidmvp_bind.app._core;

import android.os.Bundle;

import org.greenfroyo.mvp_bind.base.BaseMvpPresenter;
import org.greenfroyo.mvp_bind.base.BaseMvpViewModel;

import icepick.Icepick;


/**
 * Created by fchristysen on 1/28/16.
 * BasePresenter features :
 * # Icepick Support
 *      > Subclass doesn't have to manually save and restore isntance state
 *        just add @State annotation before variable declaration
 */
public abstract class BasePresenter<VM extends BaseMvpViewModel> extends BaseMvpPresenter<VM> {

    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        Icepick.restoreInstanceState(this, presenterState);
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
        Icepick.saveInstanceState(this, outPresenterState);
    }
}
