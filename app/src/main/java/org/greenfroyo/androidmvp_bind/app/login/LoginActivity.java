package org.greenfroyo.androidmvp_bind.app.login;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app._core.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.databinding.LoginActivityBinding;

/**
 * Created by fchristysen on 6/29/16.
 */

public class LoginActivity extends BaseToolbarActivity<LoginPresenter, LoginViewModel>
    implements View.OnClickListener{

    private LoginActivityBinding mBinding;

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected ViewDataBinding onInitView(LoginViewModel viewModel) {
        mBinding = setBindView(R.layout.login_activity);
        mBinding.setViewModel(viewModel);
        mBinding.setOnClickListener(this);
        return mBinding;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBinding.btnLogin)){
            getPresenter().onLogin();
        }
    }
}
