package org.greenfroyo.mvp_bind.presenter;

import android.os.Bundle;

import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.greenfroyo.mvp_bind.util.AppUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fchristysen on 5/20/16.
 */
public abstract class Presenter<VM extends MvpViewModel> extends MvpPresenter<VM> {
    public static final String KEY_VIEW_MODEL = "view_model";
    private final String TAG;
    private final String ID;
    private VM mViewModel;
    private ArrayList<MvpPresenter.OnDestroyListener> mListeners;

    public Presenter() {
        this.TAG = this.getClass().getSimpleName();
        this.ID = TAG + "-" + System.currentTimeMillis() + "-" + Long.toHexString(new Random().nextLong());
        this.mListeners = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    protected void create(Bundle savedPresenterState) {
        mViewModel = onRestoredViewModel(savedPresenterState);
        if (mViewModel == null) {
            mViewModel = onCreateViewModel();
        }
        onCreate(savedPresenterState);
    }

    @Override
    protected void attachView() {
        onViewAttached();
    }

    @Override
    public final VM getViewModel() {
        return mViewModel;
    }

    @Override
    protected void saveInstanceState(Bundle outPresenterState) {
        onSaveInstanceState(outPresenterState);
    }

    @Override
    protected void detachView() {
        onViewDetached();
    }

    @Override
    protected void destroy() {
        onDestroy();
        for (OnDestroyListener listener : mListeners) {
            listener.onDestroy(getID());
        }
    }

    @Override
    protected final void addOnDestroyListener(OnDestroyListener listener) {
        mListeners.add(listener);
    }

    @Override
    protected final boolean removeOnDestroyListener(OnDestroyListener listener) {
        return mListeners.remove(listener);
    }

    //region Lifecycle
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Called when this presenter is created
     *
     * @param presenterState previously saved presenter state(nullable)
     */
    protected void onCreate(Bundle presenterState) {
        AppUtil.log(TAG + " : " + "onCreate");
    }

    /**
     * Return the restored view model here, or return null if not available
     *
     * @return Restored value of the view model
     */
    protected abstract VM onRestoredViewModel(Bundle savedPresenterState);

    /**
     * Initialize the View Model here
     *
     * @return Initial value of the view model
     */
    protected abstract VM onCreateViewModel();

    /**
     * Called when activity's onSaveInstanceState is called
     *
     * @param outPresenterState instanceState specifically for presenter object
     */
    protected void onSaveInstanceState(Bundle outPresenterState) {
        AppUtil.log(TAG + " : " + "onSaveInstanceState");
    }

    /**
     * Called after view is attached to this presenter
     */
    protected void onViewAttached() {
        AppUtil.log(TAG + " : " + "onViewAttached");
    }

    /**
     * Called after view is detached to this presenter
     */
    protected void onViewDetached() {
        AppUtil.log(TAG + " : " + "onViewDetached");
    }

    /**
     * Called when the view is finishing
     */
    protected void onDestroy() {
        AppUtil.log(TAG + " : " + "onDestroy");
    }
    //endregion

}
