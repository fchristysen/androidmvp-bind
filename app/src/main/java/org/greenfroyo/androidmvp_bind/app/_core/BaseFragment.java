package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.Observable;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.mvpb.base.BaseMvpFragment;
import org.greenfroyo.mvpb.presenter.MvpPresenter;

/**
 * Created by fchristysen on 6/30/16.
 */

public abstract class BaseFragment<P extends MvpPresenter<VM>, VM extends BaseViewModel>
        extends BaseMvpFragment<P, VM> {

    protected void onViewModelChanged(Observable observable, int i) {
        if (i == BR.toastMessage) {
            if (getViewModel().needToShowToast()) {
                Toast.makeText(getActivity(), getViewModel().getToastMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
