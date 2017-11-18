package org.greenfroyo.mvpb.presenter;

import android.os.Bundle;

import org.greenfroyo.mvpb.model.MvpViewModel;

/**
 * Created by fchristysen on 5/20/16.
 */
public abstract class MvpPresenter<VM extends MvpViewModel> {

    public abstract String getID();

    protected abstract void attachView();

    protected abstract void detachView();

    public abstract VM getViewModel();

    protected abstract void create(Bundle savedPresenterState);

    protected abstract void saveInstanceState(Bundle outPresenterState);

    protected abstract void destroy();

    protected abstract void addOnDestroyListener(OnDestroyListener listener);

    protected abstract boolean removeOnDestroyListener(OnDestroyListener listener);

    interface OnDestroyListener{
        void onDestroy(String presenterId);
    }
}
