package org.greenfroyo.androidmvp_bind.app._core;

import android.os.Bundle;

import org.greenfroyo.mvp_bind.base.BaseMvpPresenter;
import org.greenfroyo.mvp_bind.base.BaseMvpViewModel;
import org.greenfroyo.mvp_bind.model.MvpViewModel;

import icepick.Icepick;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by fchristysen on 1/28/16.
 * BasePresenter features :
 * # Icepick Support
 * > Subclass doesn't have to manually save and restore instance state
 * just add @State annotation before variable declaration
 */
public abstract class BasePresenter<VM extends MvpViewModel> extends BaseMvpPresenter<VM> {

    protected CompositeSubscription mSubscription = new CompositeSubscription();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
