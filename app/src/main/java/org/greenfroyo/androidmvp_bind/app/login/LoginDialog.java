package org.greenfroyo.androidmvp_bind.app.login;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseDialog;
import org.greenfroyo.androidmvp_bind.databinding.LoginActivityBinding;

/**
 * Created by fchristysen on 7/26/16.
 */

public class LoginDialog extends BaseDialog<LoginPresenter, LoginViewModel>
        implements View.OnClickListener {

    private LoginActivityBinding mBinding;

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    public LoginDialog(Activity activity) {
        super(activity);
    }

    @Override
    protected ViewDataBinding onInitView(LoginViewModel viewModel) {
        mBinding = setBindView(R.layout.login_activity);
        mBinding.setViewModel(viewModel);
        mBinding.btnLoginDialog.setVisibility(View.GONE);
        mBinding.setOnClickListener(this);
        return mBinding;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBinding.btnLogin)) {
            getPresenter().onLogin();
        }
    }
}
