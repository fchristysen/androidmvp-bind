package org.greenfroyo.androidmvp_bind.app.intentparam.back;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.f2prateek.dart.HensonNavigable;
import com.f2prateek.dart.InjectExtra;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.databinding.IntentParamBackActivityBinding;

/**
 * Created by fchristysen on 6/7/16.
 */

public class IntentParamBackActivity extends BaseToolbarActivity<IntentParamBackPresenter, IntentParamBackViewModel> {
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
        mBinding = setBindView(R.layout.intent_param_back_activity);
        mBinding.setViewModel(getPresenter().getViewModel());
        return mBinding;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean r = super.onCreateOptionsMenu(menu);
        menu.add(R.string.menu_to_home);
        return r;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals(MVPBApp.resources().getString(R.string.menu_to_home))){
            
        }
        return super.onOptionsItemSelected(item);
    }
}
