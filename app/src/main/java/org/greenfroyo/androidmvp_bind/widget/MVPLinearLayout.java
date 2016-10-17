package org.greenfroyo.androidmvp_bind.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.mvpb.presenter.CompoundPresenterManager;
import org.greenfroyo.mvpb.presenter.PresenterFactory;
import org.greenfroyo.mvpb.view.MvpView;

/**
 * Created by fchristysen on 6/28/16.
 * This class is intended to be used as a base class when creating a compound view with LinearLayout
 * There is no need to use this view in xml.
 */

public abstract class MVPLinearLayout<P extends BasePresenter<VM>, VM extends BaseViewModel>
        extends LinearLayout
        implements MvpView<P, VM>, PresenterFactory<P> {
    private CompoundPresenterManager<P> mPresenterManager;

    public MVPLinearLayout(Context context) {
        super(context);
        initView();
    }

    public MVPLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MVPLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        mPresenterManager = new CompoundPresenterManager(this);
        onInitView();
    }

    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        T binding = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(isInEditMode()){
            inflater.inflate(layoutId, this, true);
        }else {
            binding = DataBindingUtil.inflate(inflater, layoutId, null, false);
            addView(binding.getRoot());
        }
        return binding;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(!isInEditMode()) { // need to check isInEditMode, ehcache error
            mPresenterManager.onAttachedView();
            onBindView(getPresenter().getViewModel());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenterManager.onDetachedView(getParentActivity().isFinishing());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(mPresenterManager.onRestoreInstanceState(this, state));
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable r = mPresenterManager.onSaveInstanceState(this, super.onSaveInstanceState());
        return r;
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    public P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    /**
     * Returns the unwrapped activity of the view or throws an exception.
     * @return an unwrapped activity
     */
    public Activity getParentActivity() {
        Context context = getContext();
        while (!(context instanceof Activity) && context instanceof ContextWrapper)
            context = ((ContextWrapper)context).getBaseContext();
        if (!(context instanceof Activity))
            throw new IllegalStateException("Expected an activity context, got " + context.getClass().getSimpleName());
        return (Activity)context;
    }

    /** Initialize your ViewDataBinding and other view initialization  here
     */
    public abstract void onInitView();

    /** Bind the view model into the ViewDataBinding here
     * Notes : This is called inside onAttachedView method, which ensure that the onRestoreState
     * is already called(if there's a state to be restored). This is required, as getting presenter
     * before onRestoreState will create another new presenter object even if old one exists
     * @param viewModel the object to be bind into binding class
     */
    public abstract void onBindView(VM viewModel);

    public VM getViewModel(){
        return getPresenter().getViewModel();
    }
}
