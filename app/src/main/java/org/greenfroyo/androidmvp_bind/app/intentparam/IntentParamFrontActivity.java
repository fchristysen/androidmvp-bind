package org.greenfroyo.androidmvp_bind.app.intentparam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;

import butterknife.BindView;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamFrontActivity extends BaseActivity<IntentParamFrontPresenter, IntentParamFrontViewModel> implements View.OnClickListener {
    @BindView(R.id.tv_text) protected TextView vText;
    @BindView(R.id.btn_add) protected Button vButtonAdd;
    @BindView(R.id.btn_next) protected TextView vButtonNext;

    @Override
    public IntentParamFrontPresenter createPresenter() {
        return new IntentParamFrontPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onInitView() {
        setContentView(R.layout.intent_param_front_activity);
    }

    @Override
    public void onViewModelChanged(IntentParamFrontViewModel viewModel) {
        vText.setText("Value : "+ viewModel.getValue());
    }

    @Override
    protected void onInitListener() {
        vButtonAdd.setOnClickListener(this);
        vButtonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(vButtonAdd)){
            getPresenter().onIncrementValue();
        }else if(v.equals(vButtonNext)){

        }
    }
}
