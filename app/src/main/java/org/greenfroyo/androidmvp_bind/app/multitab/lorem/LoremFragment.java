package org.greenfroyo.androidmvp_bind.app.multitab.lorem;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseFragment;
import org.greenfroyo.androidmvp_bind.databinding.LoremFragmentBinding;

/**
 * Created by fchristysen on 6/30/16.
 */

public class LoremFragment extends BaseFragment<LoremPresenter, LoremViewModel>{

    private LoremFragmentBinding mBinding;

    @Override
    public LoremPresenter createPresenter() {
        return new LoremPresenter();
    }

    @Override
    protected ViewDataBinding onInitView(LayoutInflater inflater, LoremViewModel viewModel) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.lorem_fragment, null, false);
        mBinding.setViewModel(viewModel);
        return mBinding;
    }
}
