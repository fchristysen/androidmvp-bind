package org.greenfroyo.mvp_bind.presenter;

/**
 * Created by fchristysen on 5/20/16.
 */
public interface PresenterFactory<P extends MvpPresenter> {
    P createPresenter();
}
