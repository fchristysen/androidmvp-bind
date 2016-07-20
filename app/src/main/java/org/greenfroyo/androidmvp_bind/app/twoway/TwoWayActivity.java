package org.greenfroyo.androidmvp_bind.app.twoway;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app._core.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.databinding.TwoWayActivityBinding;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayActivity extends BaseToolbarActivity<TwoWayPresenter, TwoWayViewModel> {
    private TwoWayActivityBinding mBinding;

    @Override
    protected ViewDataBinding onInitView(TwoWayViewModel viewModel) {
        mBinding = setBindView(R.layout.two_way_activity);
        mBinding.setViewModel(viewModel);
        return mBinding;
    }

    @Override
    public TwoWayPresenter createPresenter() {
        return new TwoWayPresenter();
    }
}
