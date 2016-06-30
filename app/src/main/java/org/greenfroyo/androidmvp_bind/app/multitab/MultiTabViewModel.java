package org.greenfroyo.androidmvp_bind.app.multitab;

import android.app.Fragment;
import android.databinding.Bindable;
import android.support.v4.view.PagerAdapter;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.app.multitab.lorem.LoremFragment;

/**
 * Created by fchristysen on 6/30/16.
 */

public class MultiTabViewModel extends BaseViewModel {
    private PagerAdapter mAdapter;

    @Bindable
    public PagerAdapter getAdapter(){
        return mAdapter;
    }

    public void setAdapter(PagerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
