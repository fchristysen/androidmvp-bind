package org.greenfroyo.androidmvp_bind.app._core;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.f2prateek.dart.Dart;

import org.greenfroyo.mvp_bind.presenter.PresenterFactory;
import org.greenfroyo.mvp_bind.presenter.PresenterManager;
import org.greenfroyo.mvp_bind.util.AppUtil;
import org.greenfroyo.mvp_bind.util.ViewServer;
import org.greenfroyo.mvp_bind.view.MvpView;


/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends BasePresenter<VM>, VM extends BaseViewModel>
        extends AppCompatActivity
        implements MvpView<P, VM>, PresenterFactory<P> {
    private String TAG;
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";

    private ViewDataBinding mBinding;
    private PresenterManager<P> mPresenterManager= new PresenterManager(this);
    private Observable.OnPropertyChangedCallback mPropertyChangedCallback;

    @Override
    public abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.TAG = this.getClass().getSimpleName();
        AppUtil.log(TAG + " : " + "onCreate");

        ViewServer.get(this).addWindow(this);
        Dart.inject(this);

        mPresenterManager.onRestoreInstanceState(savedInstanceState);

        mBinding = onInitView(getPresenter().getViewModel());
        mPropertyChangedCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if(i == BR.toastMessage){
                    Toast.makeText(BaseActivity.this, getPresenter().getViewModel().getToastMessage(), Toast.LENGTH_SHORT).show();
                    getPresenter().getViewModel().clearToastMessage();
                }
            }
        };
        onInitListener();
    }

    /** Inflate your layout and other initialized of view here.
     */
    protected abstract ViewDataBinding onInitView(VM viewModel);

    /** Set listener for your view here
     */
    protected void onInitListener(){

    };

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
        getPresenter().getViewModel().addOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppUtil.log(TAG + " : " + "onPause");
        mPresenterManager.onPause(isFinishing());
        getPresenter().getViewModel().removeOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterManager.onSaveInstanceState(outState);
        AppUtil.log(TAG + " : " + "onSaveInstanceState");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }
}
