package org.greenfroyo.androidmvp_bind.app.strip;

import android.databinding.ViewDataBinding;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.databinding.StripActivityBinding;

/**
 * Created by fchristysen on 8/5/16.
 */

public class StripActivity extends BaseActivity<StripPresenter, StripViewModel>{
    private StripActivityBinding mBinding;

    @Override
    public StripPresenter createPresenter() {
        return new StripPresenter();
    }

    @Override
    protected ViewDataBinding onInitView(StripViewModel viewModel) {
        mBinding = setBindView(R.layout.strip_activity);
        return mBinding;
    }
}
