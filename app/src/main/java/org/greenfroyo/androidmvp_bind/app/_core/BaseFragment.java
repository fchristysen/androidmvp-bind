package org.greenfroyo.androidmvp_bind.app._core;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.mvpb.presenter.MvpPresenter;
import org.greenfroyo.mvpb.presenter.PresenterFactory;
import org.greenfroyo.mvpb.presenter.PresenterManager;
import org.greenfroyo.mvpb.view.MvpView;

/**
 * Created by fchristysen on 6/30/16.
 */

public abstract class BaseFragment<P extends MvpPresenter<VM>, VM extends BaseViewModel>
        extends Fragment
        implements MvpView<P, VM>, PresenterFactory<P> {

    private String TAG = this.getClass().getSimpleName();
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";

    private ViewDataBinding mBinding;
    private PresenterManager<P> mPresenterManager = new PresenterManager(this);
    private Observable.OnPropertyChangedCallback mPropertyChangedCallback;

    @Override
    public abstract P createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterManager.onRestoreInstanceState(savedInstanceState);
        mPropertyChangedCallback = getPropertyChangedCallback();
    }

    public Observable.OnPropertyChangedCallback getPropertyChangedCallback() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (i == BR.toastMessage) {
                    if (getViewModel().needToShowToast()) {
                        Toast.makeText(getActivity(), getViewModel().getToastMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    /**
     * Initialize your ViewDataBinding, view initialization, and view model binding here
     *
     * @param viewModel the object to be bind into binding class
     */
    protected abstract ViewDataBinding onInitView(LayoutInflater inflater, VM viewModel);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = onInitView(inflater, getPresenter().getViewModel());
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            Bundle hierarchyState = savedInstanceState.getBundle(WINDOW_HIERARCHY_TAG);
            if (hierarchyState != null) {
                SparseArray screenState = hierarchyState.getSparseParcelableArray(WINDOW_VIEW_TAG);
                onRestoreViewState(screenState);
            }
        }
    }

    /**
     * This will be called if activity's savedInstanceState is not null
     * Some child view that's not attached yet will need viewState to call onRestoreViewState manually
     *
     * @param viewState SparseArray containing all view state in current screen
     */
    protected void onRestoreViewState(@Nullable SparseArray<Parcelable> viewState) {
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterManager.onAttachedView();
        getViewModel().addOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenterManager.onDetachedView(getActivity().isFinishing());
        getViewModel().removeOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterManager.onSaveInstanceState(outState);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    public VM getViewModel() {
        return getPresenter().getViewModel();
    }
}
