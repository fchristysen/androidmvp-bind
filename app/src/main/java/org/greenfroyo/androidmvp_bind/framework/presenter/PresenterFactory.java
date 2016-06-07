package org.greenfroyo.androidmvp_bind.framework.presenter;

/**
 * Created by fchristysen on 5/20/16.
 */
public interface PresenterFactory<P extends MvpPresenter> {
    P createPresenter();
}
