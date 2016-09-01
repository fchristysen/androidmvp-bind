package org.greenfroyo.androidmvp_bind.app._core.error;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.databinding.BaseErrorActivityBinding;

/**
 * Created by fchristysen on 7/20/16.
 */

public abstract class BaseErrorActivity<P extends BaseErrorPresenter<VM>, VM extends BaseErrorViewModel> extends BaseToolbarActivity<P, VM> {

    private BaseErrorActivityBinding mErrorBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        mErrorBinding = super.setBindView(R.layout.base_error_activity);
        mErrorBinding.setViewModel(getViewModel());
        T binding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
        mErrorBinding.errorContent.addView(binding.getRoot());

        setupButton();
        return binding;
    }

    private void setupButton() {
        mErrorBinding.errorButton.setOnClickListener(v -> onErrorButtonClicked());
    }

    /**
     * This method will be called when button in error screen is pressed
     * The default parent implementation will set error to false, if this behaviour is not desired
     * , child implementation could omit calling super method
     */
    protected void onErrorButtonClicked() {
        getViewModel().hideError();
    }
}
