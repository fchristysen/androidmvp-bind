package org.greenfroyo.androidmvp_bind.app._core;

import org.greenfroyo.mvp_bind.base.BaseMvpPresenter;
import org.greenfroyo.mvp_bind.model.MvpViewModel;

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
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
