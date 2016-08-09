package org.greenfroyo.androidmvp_bind.app.login;

import android.app.Activity;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseDialog;
import org.greenfroyo.androidmvp_bind.app._core.BaseDialogFragment;
import org.greenfroyo.androidmvp_bind.databinding.LoginActivityBinding;
import org.greenfroyo.androidmvp_bind.databinding.LoginDialogBinding;

/**
 * Created by fchristysen on 7/26/16.
 */

public class LoginDialog extends BaseDialogFragment<LoginPresenter, LoginViewModel>
        implements View.OnClickListener {

    private LoginDialogBinding mBinding;

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    public LoginDialog() {
        super();
    }

    @Override
    protected ViewDataBinding onInitView(LayoutInflater infalter, LoginViewModel viewModel) {
        mBinding = setBindView(R.layout.login_dialog);
        mBinding.setViewModel(viewModel);
        mBinding.setOnClickListener(this);
        return mBinding;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBinding.btnLogin)) {
            getPresenter().onLogin();
        }
    }

    @Override
    protected void onViewModelChanged(Observable observable, int i) {
        super.onViewModelChanged(observable, i);
        if(i == BR.state) {
            if(getViewModel().getState() == LoginViewModel.STATE_LOGGEDIN){
                setResult(Activity.RESULT_OK);
                dismiss();
            }
        }
    }
}
