package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.Observable;
import android.os.Bundle;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.f2prateek.dart.Dart;

import org.greenfroyo.mvpb.base.BaseMvpActivity;
import org.greenfroyo.mvpb.presenter.MvpPresenter;


/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends MvpPresenter<VM>, VM extends BaseViewModel>
        extends BaseMvpActivity<P, VM> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dart.inject(this);
    }

    protected void onViewModelChanged(Observable observable, int i) {
        if (i == BR.toastMessage) {
            if (getViewModel().needToShowToast()) {
                Toast.makeText(BaseActivity.this, getViewModel().getToastMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
