package org.greenfroyo.mvp_bind.presenter;

import android.os.Bundle;

import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.greenfroyo.mvp_bind.view.MvpView;

/**
 * Created by fchristysen on 5/20/16.
 */
public interface MvpPresenter<VM extends MvpViewModel> {

    String getID();

    void attachView();

    void detachView();

    VM getViewModel();

    void create(Bundle savedPresenterState);

    void saveInstanceState(Bundle outPresenterState);

    void destroy();

    void addOnDestroyListener(OnDestroyListener listener);

    boolean removeOnDestroyListener(OnDestroyListener listener);

    interface OnDestroyListener{
        void onDestroy(String presenterId);
    }
}
