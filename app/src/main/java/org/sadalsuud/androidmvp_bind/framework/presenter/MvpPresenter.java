package org.sadalsuud.androidmvp_bind.framework.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.sadalsuud.androidmvp_bind.framework.model.MvpViewModel;
import org.sadalsuud.androidmvp_bind.framework.view.MvpView;

/**
 * Created by fchristysen on 5/20/16.
 */
public interface MvpPresenter<VM extends MvpViewModel> {

    String getID();

    void attachView(@NonNull MvpView view);

    void detachView();

    MvpView getView();

    void create(Bundle savedPresenterState);

    void onSaveInstanceState(Bundle outPresenterState);

    void destroy();

    void addOnDestroyListener(OnDestroyListener listener);

    boolean removeOnDestroyListener(OnDestroyListener listener);

    interface OnDestroyListener{
        void onDestroy(String presenterId);
    }
}
