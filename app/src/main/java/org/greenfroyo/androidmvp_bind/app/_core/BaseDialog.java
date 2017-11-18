package org.greenfroyo.androidmvp_bind.app._core;

import android.app.Activity;
import android.databinding.Observable;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.mvpb.base.BaseMvpDialog;
import org.greenfroyo.mvpb.presenter.MvpPresenter;

/**
 * Created by fchristysen on 7/26/16.
 */

public abstract class BaseDialog<P extends MvpPresenter<VM>, VM extends BaseViewModel>
        extends BaseMvpDialog<P, VM> {

    public BaseDialog(Activity activity) {
        super(activity);
        setOwnerActivity(activity);
    }

    protected void onViewModelChanged(Observable observable, int i) {
        if (i == BR.toastMessage) {
            if (getViewModel().needToShowToast()) {
                Toast.makeText(getOwnerActivity(), getViewModel().getToastMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
