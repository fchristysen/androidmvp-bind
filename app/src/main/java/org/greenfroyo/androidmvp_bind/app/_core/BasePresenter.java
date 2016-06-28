package org.greenfroyo.androidmvp_bind.app._core;

import android.os.Bundle;
import android.util.Log;

import org.greenfroyo.mvp_bind.presenter.Presenter;
import org.greenfroyo.mvp_bind.view.MvpView;
import org.parceler.ParcelerRuntimeException;
import org.parceler.Parcels;

import icepick.Icepick;


/**
 * Created by fchristysen on 1/28/16.
 * BasePresenter features :
 * # Icepick Support
 *      > Subclass doesn't have to manually save and restore isntance state
 *        just add @State annotation before variable declaration
 */
public abstract class BasePresenter<VM extends BaseViewModel> extends Presenter<VM> {

    private VM mViewModel;

    @Override
    public final void create(Bundle savedPresenterState) {
        Icepick.restoreInstanceState(this, savedPresenterState);
        if(savedPresenterState!=null && savedPresenterState.containsKey(KEY_VIEW_MODEL)){
            try {
                mViewModel = Parcels.unwrap(savedPresenterState.getParcelable(KEY_VIEW_MODEL));
            } catch (ParcelerRuntimeException e){
                Log.e("ParcelerRuntimeError", mViewModel.getClass() + " does not have @Parcel annotation. the view model cannot be parceled.");
                Log.w("ParcelerRuntimeError", e.getMessage());
            }
        }
        if(mViewModel == null){
            mViewModel = onInitViewModel();
        }
        super.create(savedPresenterState);
        getViewModel().attachOnPropertyChangeCallback();
    }

    @Override
    protected VM onRestoredViewModel() {
        return mViewModel;
    }

    @Override
    public final void attachView(MvpView view) {
        super.attachView(view);
    }

    @Override
    public final void detachView() {
        super.detachView();
    }

    @Override
    public final void saveInstanceState(Bundle outPresenterState) {
        Icepick.saveInstanceState(this, outPresenterState);
        try {
            outPresenterState.putParcelable(KEY_VIEW_MODEL, Parcels.wrap(getViewModel()));
        } catch (ParcelerRuntimeException e){
            Log.e("ParcelerRuntimeError", mViewModel.getClass() + " does not have @Parcel annotation. the view model cannot be parceled.");
            Log.w("ParcelerRuntimeError", e.getMessage());
        }
        super.saveInstanceState(outPresenterState);
    }

    @Override
    public final void destroy() {
        super.destroy();
        getViewModel().detachOnPropertyChangeCallback();
    }
}
