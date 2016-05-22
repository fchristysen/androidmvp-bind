package org.sadalsuud.androidmvp_bind.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import org.sadalsuud.androidmvp_bind.framework.AppUtil;
import org.sadalsuud.androidmvp_bind.framework.model.MvpViewModel;
import org.sadalsuud.androidmvp_bind.framework.presenter.MvpPresenter;
import org.sadalsuud.androidmvp_bind.framework.presenter.PresenterFactory;
import org.sadalsuud.androidmvp_bind.framework.presenter.PresenterManager;
import org.sadalsuud.androidmvp_bind.framework.view.MvpView;


/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends MvpPresenter, VM extends MvpViewModel> extends AppCompatActivity implements MvpView<P, VM>, PresenterFactory<P> {
    private String TAG;
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";

    private PresenterManager<P> mPresenterManager= new PresenterManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.TAG = this.getClass().getSimpleName();
        AppUtil.log(TAG + " : " + "onCreate");

        ViewServer.get(this).addWindow(this);
        mPresenterManager.onRestoreInstanceState(savedInstanceState);

        onInitView();
        onInitState();
        onInitListener();
    }

    /** Inflate your layout and other initialized of view here.
     */
    protected abstract void onInitView();

    /** Set your default state for you view here.
     */
    protected abstract void onInitState();

    /** Set listener for your view here
     */
    protected abstract void onInitListener();

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            Bundle hierarchyState = savedInstanceState.getBundle(WINDOW_HIERARCHY_TAG);
            if(hierarchyState!=null){
                SparseArray screenState = hierarchyState.getSparseParcelableArray(WINDOW_VIEW_TAG);
                onRestoreViewState(screenState);
            }
        }
    }

    /**
     * This will be called if activity's savedInstanceState is not null
     * Some child view that's not attached yet will need viewState to call onRestoreViewState manually
     * @param viewState SparseArray containing all view state in current screen
     */
    protected void onRestoreViewState(@Nullable SparseArray<Parcelable> viewState){};

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.log(TAG + " : " + "onResume");
        mPresenterManager.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppUtil.log(TAG + " : " + "onPause");
        mPresenterManager.onPause(isFinishing());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterManager.onSaveInstanceState(outState);
        AppUtil.log(TAG + " : " + "onSaveInstanceState");
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    @Override
    public abstract P createPresenter();

}
