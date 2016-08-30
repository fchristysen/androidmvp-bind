package org.greenfroyo.mvpb.base;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import org.greenfroyo.mvpb.presenter.MvpPresenter;
import org.greenfroyo.mvpb.presenter.PresenterFactory;
import org.greenfroyo.mvpb.presenter.PresenterManager;
import org.greenfroyo.mvpb.view.MvpView;

/**
 * Created by fchristysen on 7/26/16.
 */

public abstract class BaseMvpDialog<P extends MvpPresenter<VM>, VM extends BaseMvpViewModel>
        extends Dialog
        implements MvpView<P, VM>, PresenterFactory<P> {

    private String TAG = this.getClass().getSimpleName();

    private ViewDataBinding mBinding;
    private PresenterManager<P> mPresenterManager = new PresenterManager(this);
    private Observable.OnPropertyChangedCallback mPropertyChangedCallback;
    private DialogListener mDialogListener;

    @Override
    public abstract P createPresenter();

    public BaseMvpDialog(Activity activity) {
        super(activity);
        setOwnerActivity(activity);
    }

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

    public Observable.OnPropertyChangedCallback getPropertyChangedCallback() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                onViewModelChanged(observable, i);
            }
        };
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenterManager.onAttachedView();
        getViewModel().addOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenterManager.onDetachedView(getOwnerActivity().isFinishing());
        getViewModel().removeOnPropertyChangedCallback(mPropertyChangedCallback);
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    public VM getViewModel() {
        return getPresenter().getViewModel();
    }

    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        T viewDataBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
        setContentView(viewDataBinding.getRoot());
        return viewDataBinding;
    }

    protected void onViewModelChanged(Observable observable, int i) {
    }

    public void setDialogListener(DialogListener dialogListener) {
        mDialogListener = dialogListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mDialogListener != null) {
            mDialogListener.onDismiss(this);
        }
    }

    public void complete() {
        this.dismiss();
        if (mDialogListener != null) {
            mDialogListener.onComplete(this);
        }
    }

    public void cancel() {
        super.cancel();
        //mDialogListener.onDismiss(this);  //dismiss is already called from super.cancel();
        if (mDialogListener != null) {
            mDialogListener.onCancel(this);
        }
    }

    public interface DialogListener {
        void onComplete(Dialog dialog);

        void onCancel(Dialog dialog);

        void onDismiss(Dialog dialog);
    }
}
