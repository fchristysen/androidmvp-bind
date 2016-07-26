package org.greenfroyo.androidmvp_bind.app._core.toolbar;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.databinding.BaseToolbarActivityBinding;

/**
 * Created by fchristysen on 7/20/16.
 */

public abstract class BaseToolbarActivity<P extends BaseToolbarPresenter<VM>, VM extends BaseToolbarViewModel> extends BaseActivity<P, VM> {

    private BaseToolbarActivityBinding mToolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().increaseActivityCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().decreaseActivityCount();
    }

    public <T extends ViewDataBinding> T setBindView(int layoutId) {
        mToolbarBinding = super.setBindView(R.layout.base_toolbar_activity);
        setSupportActionBar(mToolbarBinding.toolbar);
        T binding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
        mToolbarBinding.toolbarContent.addView(binding.getRoot());
        return binding;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(R.string.menu_show_activity_count);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().toString().equals(getContext().getString(R.string.menu_show_activity_count))){
            getPresenter().showActivityCount();
        }
        return super.onOptionsItemSelected(item);
    }
}
