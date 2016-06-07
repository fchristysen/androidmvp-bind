package org.greenfroyo.androidmvp_bind.app.intentparam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamActivity extends BaseActivity<IntentParamPresenter, IntentParamViewModel>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInitState() {

    }

    @Override
    protected void onInitListener() {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreViewState(@Nullable SparseArray viewState) {
        super.onRestoreViewState(viewState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public IntentParamPresenter createPresenter() {
        return new IntentParamPresenter();
    }

    @Override
    public void onViewModelChanged(IntentParamViewModel viewModel) {

    }
}
