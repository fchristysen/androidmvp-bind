package org.greenfroyo.androidmvp_bind.app.compoundview;

import android.databinding.ViewDataBinding;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.databinding.CompoundViewActivityBinding;

/**
 * Created by fchristysen on 6/27/16.
 * This page demonstrate :
 *      - Capability of a compound view to have its own presenter and view model
 *      - Capability of retaining the compound view model through save and restore instance state
 */

public class CompoundViewActivity extends BaseToolbarActivity<CompoundViewPresenter, CompoundViewViewModel>
        implements View.OnClickListener{
    private CompoundViewActivityBinding mBinding;

    @Override
    protected ViewDataBinding onInitView(CompoundViewViewModel viewModel) {
        mBinding = setBindView(R.layout.compound_view_activity);
        mBinding.setViewModel(viewModel);
        mBinding.btnReset.setOnClickListener(this);
        return mBinding;
    }

    @Override
    public CompoundViewPresenter createPresenter() {
        return new CompoundViewPresenter();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBinding.btnReset)){
            getPresenter().resetAll();
        }
    }
}
