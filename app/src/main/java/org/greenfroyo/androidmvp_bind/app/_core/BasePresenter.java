package org.greenfroyo.androidmvp_bind.app._core;

import android.os.Bundle;

import org.greenfroyo.mvp_bind.presenter.Presenter;
import org.greenfroyo.mvp_bind.view.MvpView;

import icepick.Icepick;


/**
 * Created by fchristysen on 1/28/16.
 * BasePresenter features :
 * # Icepick Support
 *      > Subclass doesn't have to manually save and restore isntance state
 *        just add @State annotation before variable declaration
 */
public abstract class BasePresenter<VM extends BaseViewModel> extends Presenter<VM> {

    @Override
    public final void create(Bundle savedPresenterState) {
        Icepick.restoreInstanceState(this, savedPresenterState);
        super.create(savedPresenterState);
        getViewModel().attachOnPropertyChangeCallback();
    }

    @Override
    public final void attachView(MvpView view) {
        super.attachView(view);
    }

    @Override
    public final void detachView() {
        super.detachView();
    }

    @Override
    public final void saveInstanceState(Bundle outPresenterState) {
        Icepick.saveInstanceState(this, outPresenterState);
        super.onSaveInstanceState(outPresenterState);
    }

    @Override
    public final void destroy() {
        super.destroy();
        getViewModel().detachOnPropertyChangeCallback();
    }
}
