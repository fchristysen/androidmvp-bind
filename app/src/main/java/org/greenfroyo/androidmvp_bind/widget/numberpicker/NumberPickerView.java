package org.greenfroyo.androidmvp_bind.widget.numberpicker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.databinding.NumberPickerViewBinding;
import org.greenfroyo.androidmvp_bind.widget.MVPLinearLayout;

/**
 * Created by fchristysen on 6/27/16.
 */

public class NumberPickerView extends MVPLinearLayout<NumberPickerPresenter, NumberPickerViewModel>
    implements View.OnClickListener{

    private NumberPickerViewBinding mBinding;

    public NumberPickerView(Context context) {
        super(context);
        initView();
    }

    public NumberPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NumberPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public final void initView(){
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.number_picker_view, null, false);
        addView(mBinding.getRoot());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBinding.setViewModel(getPresenter().getViewModel());
        mBinding.setListener(this);
    }

    @Override
    public NumberPickerPresenter createPresenter() {
        return new NumberPickerPresenter();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBinding.ivAdd)){
            getPresenter().addValue();
        }else if(v.equals(mBinding.ivSub)){
            getPresenter().substractValue();
        }
    }
}
