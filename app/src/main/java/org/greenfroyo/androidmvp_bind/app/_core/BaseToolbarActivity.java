package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.databinding.BaseToolbarLayoutBinding;

/**
 * Created by fchristysen on 7/20/16.
 */

public abstract class BaseToolbarActivity<P extends BasePresenter<VM>, VM extends BaseViewModel> extends BaseActivity<P, VM>{

    private BaseToolbarLayoutBinding mToolbarBinding;

    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        mToolbarBinding = super.setBindView(R.layout.base_toolbar_layout);
        setSupportActionBar(mToolbarBinding.toolbar);
        T binding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
        mToolbarBinding.mainContent.addView(binding.getRoot());
        return binding;
    }
}
