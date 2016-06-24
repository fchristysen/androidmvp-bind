package org.greenfroyo.androidmvp_bind.app.intentparam.back;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.f2prateek.dart.InjectExtra;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.databinding.IntentParamBackActivityBinding;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamBackActivity extends BaseActivity<IntentParamBackPresenter, IntentParamBackViewModel> {
    @InjectExtra int mValue;

    private IntentParamBackActivityBinding mBinding;

    @Override
    public IntentParamBackPresenter createPresenter() {
        return new IntentParamBackPresenter(mValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ViewDataBinding onInitView(IntentParamBackViewModel viewModel) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.intent_param_back_activity);
        mBinding.setViewModel(getPresenter().getViewModel());
        return mBinding;
    }

    @Override
    protected void onInitListener() {
    }


}
