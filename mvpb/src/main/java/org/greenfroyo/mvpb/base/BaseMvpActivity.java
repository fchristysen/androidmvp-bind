package org.greenfroyo.mvpb.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import org.greenfroyo.mvpb.presenter.MvpPresenter;
import org.greenfroyo.mvpb.presenter.PresenterFactory;
import org.greenfroyo.mvpb.presenter.PresenterManager;
import org.greenfroyo.mvpb.view.MainPropertyChangeCallback;
import org.greenfroyo.mvpb.view.MvpView;


/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseMvpActivity<P extends MvpPresenter<VM>, VM extends BaseMvpViewModel>
        extends AppCompatActivity
        implements MvpView<P, VM>, PresenterFactory<P> {

    private String TAG = this.getClass().getSimpleName();
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";

    private ViewDataBinding mBinding;
    private PresenterManager<P> mPresenterManager = new PresenterManager(this);
    private MainPropertyChangeCallback mPropertyChangedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterManager.onRestoreInstanceState(savedInstanceState);
        mPropertyChangedCallback = getPropertyChangedCallback();

        mBinding = onInitView(getPresenter().getViewModel());
    }

    /**
     * Initialize your ViewDataBinding, view initialization, and view model binding here
     *
     * @param viewModel the object to be bind into binding class
     */
    protected abstract ViewDataBinding onInitView(VM viewModel);

    public MainPropertyChangeCallback getPropertyChangedCallback() {
        return new MainPropertyChangeCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                onViewModelChanged(observable, i);
            }
        };
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            Bundle hierarchyState = savedInstanceState.getBundle(WINDOW_HIERARCHY_TAG);
            if (hierarchyState != null) {
                SparseArray screenState = hierarchyState.getSparseParcelableArray(WINDOW_VIEW_TAG);
                onRestoreViewState(screenState);
            }
        }
    }

    /**
     * This will be called if there is previously saved savedInstanceState
     * Some child view may not be attached yet to its activity during onRestoreInstanceState
     * therefore the viewState must be passed manually.
     *
     * @param viewState SparseArray containing all view state in current screen
     */
    protected void onRestoreViewState(@Nullable SparseArray<Parcelable> viewState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenterManager.onAttachedView();
        getViewModel().addOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenterManager.onDetachedView(isFinishing());
        getViewModel().removeOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterManager.onSaveInstanceState(outState);
    }

    protected void onViewModelChanged(Observable observable, int i) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    public VM getViewModel() {
        return getPresenter().getViewModel();
    }

    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId, DataBindingUtil.getDefaultComponent());
    }
}
