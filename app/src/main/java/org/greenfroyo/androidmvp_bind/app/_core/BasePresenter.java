package org.greenfroyo.androidmvp_bind.app._core;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenfroyo.androidmvp_bind.framework.model.MvpViewModel;
import org.greenfroyo.androidmvp_bind.framework.presenter.Presenter;

import icepick.Icepick;


/**
 * Created by fchristysen on 1/28/16.
 * BasePresenter features :
 * # Icepick Support
 *      > Subclass doesn't have to manually save and restore isntance state
 *        just add @State annotation before variable declaration
 */
public abstract class BasePresenter<VM extends MvpViewModel> extends Presenter<VM> {

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
