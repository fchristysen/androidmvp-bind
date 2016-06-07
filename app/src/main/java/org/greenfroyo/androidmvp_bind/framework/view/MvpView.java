package org.greenfroyo.androidmvp_bind.framework.view;

import org.greenfroyo.androidmvp_bind.framework.model.MvpViewModel;
import org.greenfroyo.androidmvp_bind.framework.presenter.MvpPresenter;

/**
 * Created by fchristysen on 1/21/16.
 */
public interface MvpView<P extends MvpPresenter, VM extends MvpViewModel> {
    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onCreate/onPause and onAttachedToWindow/onDetachedFromWindow calls
     *
     * @return a currently attached presenter or null.
     */
    P getPresenter();

    /** This method will be called when there's a changed in the ViewModel
     */
    void onViewModelChanged(VM viewModel);
}
