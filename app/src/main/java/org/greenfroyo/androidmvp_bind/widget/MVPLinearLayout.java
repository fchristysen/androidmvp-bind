package org.greenfroyo.androidmvp_bind.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.LinearLayout;

import org.greenfroyo.mvp_bind.model.MvpViewModel;
import org.greenfroyo.mvp_bind.presenter.CompoundPresenterManager;
import org.greenfroyo.mvp_bind.presenter.MvpPresenter;
import org.greenfroyo.mvp_bind.presenter.PresenterFactory;
import org.greenfroyo.mvp_bind.view.MvpView;

/**
 * Created by fchristysen on 6/28/16.
 * This class is intended to be used as a base class when creating a compound view with LinearLayout
 * There is no need to use this view in xml.
 */

public abstract class MVPLinearLayout<P extends MvpPresenter, VM extends MvpViewModel>
        extends LinearLayout
        implements MvpView<P, VM>, PresenterFactory<P> {
    private CompoundPresenterManager<P> mPresenterManager;

    public MVPLinearLayout(Context context) {
        super(context);
        init();
    }

    public MVPLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MVPLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPresenterManager = new CompoundPresenterManager(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenterManager.onAttachedView(this);
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
}
