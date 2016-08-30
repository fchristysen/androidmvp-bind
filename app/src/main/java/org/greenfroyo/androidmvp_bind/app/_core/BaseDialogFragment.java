package org.greenfroyo.androidmvp_bind.app._core;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
 * Created by fchristysen on 7/26/16.
 */

public abstract class BaseDialogFragment<P extends MvpPresenter<VM>, VM extends BaseViewModel>
        extends DialogFragment
        implements MvpView<P, VM>, PresenterFactory<P> {

    private String TAG = this.getClass().getSimpleName();
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";
    protected final String REQUEST_CODE = "app:requestCode";
    protected final String CALLER_TYPE = "app:callerType";

    /** Standard dialog result: operation canceled. */
    public static final int RESULT_CANCELED = 0;
    /** Standard dialog result: operation succeeded. */
    public static final int RESULT_OK = -1;

    public static final int CALLER_TYPE_NOT_SET = 0;
    public static final int CALLER_TYPE_ACTIVITY = 1;
    public static final int CALLER_TYPE_FRAGMENT = 2;

    private LayoutInflater mLayoutInflater;
    private ViewDataBinding mBinding;
    private PresenterManager<P> mPresenterManager = new PresenterManager(this);
    private Observable.OnPropertyChangedCallback mPropertyChangedCallback;
    private int mCallerType;
    private int mRequestCode;
    private int mResultCode;
    private Bundle mResultBundle;

    @Override
    public abstract P createPresenter();

    public BaseDialogFragment() {
        super();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutInflater = getLayoutInflater(savedInstanceState);
        mPresenterManager.onRestoreInstanceState(savedInstanceState);
        mPropertyChangedCallback = getPropertyChangedCallback();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = onInitView(inflater, getPresenter().getViewModel());
        return mBinding.getRoot();
    }

    /**
     * Initialize your ViewDataBinding, view initialization, and view model binding here
     *
     * @param viewModel the object to be bind into binding class
     */
    protected abstract ViewDataBinding onInitView(LayoutInflater inflater, VM viewModel);

    public Observable.OnPropertyChangedCallback getPropertyChangedCallback() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                onViewModelChanged(observable, i);
            }
        };
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            Bundle hierarchyState = savedInstanceState.getBundle(WINDOW_HIERARCHY_TAG);
            mRequestCode = savedInstanceState.getInt(REQUEST_CODE, 0);
            mCallerType = savedInstanceState.getInt(CALLER_TYPE, 0);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterManager.onSaveInstanceState(outState);
        outState.putInt(REQUEST_CODE, mRequestCode);
        outState.putInt(CALLER_TYPE, mCallerType);
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    public VM getViewModel() {
        return getPresenter().getViewModel();
    }

    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        T viewDataBinding = DataBindingUtil.inflate(mLayoutInflater, layoutId, null, false);
        return viewDataBinding;
    }

    protected void onViewModelChanged(Observable observable, int i) {
        if (i == BR.toastMessage) {
            if (getViewModel().needToShowToast()) {
                Toast.makeText(getActivity(), getViewModel().getToastMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @param fragment fragment to receive result, must implement DialogFragmentListener
     * @param requestCode identifier to distinguished each request
     */
    public void setTargetFragment(Fragment fragment, int requestCode){
        super.setTargetFragment(fragment, requestCode);
        mCallerType = CALLER_TYPE_FRAGMENT;
        mRequestCode = requestCode;
    }

    /** Make sure your caller activity implement DialogFragmentListener to able to receive results
     * @param requestCode identifier to distinguished each request
     */
    public void setTargetActivity(int requestCode) {
        mCallerType = CALLER_TYPE_ACTIVITY;
        mRequestCode = requestCode;
    }

    protected void setResult(int resultCode){
        setResult(resultCode, null);
    }

    protected void setResult(int resultCode, Bundle bundle){
        mResultCode = resultCode;
        mResultBundle = bundle;
    }

    // this must be implemented in onCancel, as onDismiss will called when rotating stack of DialogFragment
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if(mCallerType == CALLER_TYPE_FRAGMENT && getTargetFragment() instanceof DialogFragmentListener){
            ((DialogFragmentListener)getTargetFragment()).onFragmentResult(mRequestCode, mResultCode, mResultBundle);
        }
        if(mCallerType == CALLER_TYPE_ACTIVITY && getActivity() instanceof DialogFragmentListener){
            ((DialogFragmentListener)getActivity()).onFragmentResult(mRequestCode, mResultCode, mResultBundle);
        }
    }

    public interface DialogFragmentListener{
        void onFragmentResult(int requestCode, int resultCode, Bundle bundle);
    }
}
