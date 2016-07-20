package org.greenfroyo.androidmvp_bind.app.compoundview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app._core.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.databinding.CompoundViewActivityBinding;

/**
 * Created by fchristysen on 6/27/16.
 */

public class CompoundViewActivity extends BaseToolbarActivity<CompoundViewPresenter, CompoundViewViewModel> {
    private CompoundViewActivityBinding mBinding;

    @Override
    protected ViewDataBinding onInitView(CompoundViewViewModel viewModel) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.compound_view_activity);
        mBinding.setViewModel(viewModel);
        return mBinding;
    }

    @Override
    public CompoundViewPresenter createPresenter() {
        return new CompoundViewPresenter();
    }
}
