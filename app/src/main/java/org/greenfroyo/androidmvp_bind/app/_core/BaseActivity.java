package org.greenfroyo.androidmvp_bind.app._core;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.f2prateek.dart.Dart;

import org.greenfroyo.androidmvp_bind.framework.presenter.PresenterFactory;
import org.greenfroyo.androidmvp_bind.framework.presenter.PresenterManager;
import org.greenfroyo.androidmvp_bind.framework.view.MvpView;
import org.greenfroyo.androidmvp_bind.util.AppUtil;
import org.greenfroyo.androidmvp_bind.util.ViewServer;
import org.greenrobot.eventbus.Subscribe;


/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends BasePresenter<VM>, VM extends BaseViewModel> extends AppCompatActivity implements MvpView<P, VM>, PresenterFactory<P> {
    private String TAG;
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";

    private ViewDataBinding mBinding;
    private PresenterManager<P> mPresenterManager= new PresenterManager(this);

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
        getPresenter().getViewModel().getEventBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppUtil.log(TAG + " : " + "onPause");
        mPresenterManager.onPause(isFinishing());
        getPresenter().getViewModel().getEventBus().unregister(this);
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

    @Subscribe
    public void onSnackbarEvent(BaseViewModel.SnackbarEvent event){
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
