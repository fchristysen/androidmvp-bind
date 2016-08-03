package org.greenfroyo.mvp_bind.presenter;

import android.os.Bundle;

import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.greenfroyo.mvp_bind.view.MvpView;
import org.greenfroyo.mvp_bind.util.AppUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fchristysen on 5/20/16.
 */
public abstract class Presenter<VM extends MvpViewModel> implements MvpPresenter<VM> {
    public static final String KEY_VIEW_MODEL="view_model";
    private final String TAG;
    private final String ID;
    private VM mViewModel;
    private WeakReference<MvpView> mView;
    private ArrayList<MvpPresenter.OnDestroyListener> mListeners;

    public Presenter(){
        this.TAG = this.getClass().getSimpleName();
        this.ID = TAG + "-" + System.currentTimeMillis() + "-" + Long.toHexString(new Random().nextLong());
        this.mListeners = new ArrayList<>();
    }

    public final String getID() {
        return ID;
    }

    @Override
    public void create(Bundle savedPresenterState){
        mViewModel = onRestoredViewModel(savedPresenterState);
        if(mViewModel == null){
            mViewModel = onCreateViewModel();
        }
        onCreate(savedPresenterState);
    }

    @Override
    public void attachView(MvpView view){
        this.mView = new WeakReference<>(view);
        onViewAttached();
    }

    public final VM getViewModel() {
        return mViewModel;
    }

    @Override
    public void saveInstanceState(Bundle outPresenterState) {
        onSaveInstanceState(outPresenterState);
    }

    @Override
    public void detachView(){
        onDetachView();
        this.mView = null;

        onViewDetached();
    }

    @Override
    public final MvpView getView(){
        if(mView == null)
            return null;
        else
            return mView.get();
    }

    @Override
    public void destroy() {
        onDestroy();
        for(OnDestroyListener listener:mListeners){
            listener.onDestroy(getID());
        }
    }

    @Override
    public final void addOnDestroyListener(OnDestroyListener listener) {
        mListeners.add(listener);
    }

    @Override
    public final boolean removeOnDestroyListener(OnDestroyListener listener) {
        return mListeners.remove(listener);
    }

    //region Lifecycle
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Called when this presenter is created
     * @param presenterState previously saved presenter state(nullable)
     */
    public void onCreate(Bundle presenterState) {
        AppUtil.log(TAG + " : " + "onCreate");
    }

    /**
     * Return the restored view model here, or return null if not available
     * @return Restored value of the view model
     */
    protected abstract VM onRestoredViewModel(Bundle savedPresenterState);

    /**
     * Initialize the View Model here
     * @return Initial value of the view model
     */
    public abstract VM onCreateViewModel();

    /**
     * Called when activity's onSaveInstanceState is called
     * @param outPresenterState instanceState specifically for presenter object
     */
    public void onSaveInstanceState(Bundle outPresenterState) {
        AppUtil.log(TAG + " : " + "onSaveInstanceState");
    }

    /**
     * Called after view is attached to this presenter
     */
    protected void onViewAttached(){
        AppUtil.log(TAG + " : " + "onViewAttached");
    }

    /**
     * Called before view is detached to this presenter
     */
    protected void onDetachView(){
        AppUtil.log(TAG + " : " + "onDetachedView");
    }

    /**
     * Called after view is detached to this presenter
     */
    protected void onViewDetached(){
        AppUtil.log(TAG + " : " + "onViewDetached");
    }

    /**
     * Called when the view is finishing
     */
    protected void onDestroy(){
        AppUtil.log(TAG + " : " + "onDestroy");
    }
    //endregion

}
