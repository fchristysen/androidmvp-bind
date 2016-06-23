package org.greenfroyo.androidmvp_bind.app.twoway;

import android.databinding.DataBindingUtil;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.databinding.TwoWayActivityBinding;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayActivity extends BaseActivity<TwoWayPresenter, TwoWayViewModel> {
    private TwoWayActivityBinding mBinding;

    @Override
    protected void onInitView(TwoWayViewModel viewModel) {
        super.onInitView(viewModel);
        mBinding = DataBindingUtil.setContentView(this, R.layout.two_way_activity);
        mBinding.setViewModel(viewModel);
    }

    @Override
    public TwoWayPresenter createPresenter() {
        return new TwoWayPresenter();
    }
}
