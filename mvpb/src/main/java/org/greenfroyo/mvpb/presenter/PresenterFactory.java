package org.greenfroyo.mvpb.presenter;

/**
 * Created by fchristysen on 5/20/16.
 */
public interface PresenterFactory<P extends MvpPresenter> {
    P createPresenter();
}
