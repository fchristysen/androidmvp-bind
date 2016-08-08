package org.greenfroyo.mvp_bind.base;

import android.os.Bundle;
import android.util.Log;

import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.greenfroyo.mvp_bind.presenter.Presenter;
import org.parceler.ParcelerRuntimeException;
import org.parceler.Parcels;

/**
 * Created by fchristysen on 7/17/16.
 */
public abstract class BaseMvpPresenter<VM extends MvpViewModel> extends Presenter<VM> {
    private VM mViewModel;

    @Override
    public final void create(Bundle savedPresenterState) {
        super.create(savedPresenterState);
        getViewModel().onAttached();
    }

    @Override
    protected VM onRestoredViewModel(Bundle savedPresenterState) {
        if (savedPresenterState != null && savedPresenterState.containsKey(KEY_VIEW_MODEL)) {
            try {
                mViewModel = Parcels.unwrap(savedPresenterState.getParcelable(KEY_VIEW_MODEL));
            } catch (ParcelerRuntimeException e) {
                Log.e("ParcelerRuntimeError", this.getClass() + " can not parcel the view model please check if it has @Parcel annotation.");
                Log.w("ParcelerRuntimeError", e.getMessage());
            }
        }
        return mViewModel;
    }

    @Override
    public final void onSaveInstanceState(Bundle outPresenterState) {
        try {
            outPresenterState.putParcelable(KEY_VIEW_MODEL, Parcels.wrap(getViewModel()));
        } catch (ParcelerRuntimeException e) {
            Log.e("ParcelerRuntimeError", this.getClass() + " can not parcel the view model please check if it has @Parcel annotation.");
            Log.w("ParcelerRuntimeError", e.getMessage());
        }
        super.onSaveInstanceState(outPresenterState);
    }

    @Override
    public final void destroy() {
        super.destroy();
        getViewModel().onDetached();
    }
}
