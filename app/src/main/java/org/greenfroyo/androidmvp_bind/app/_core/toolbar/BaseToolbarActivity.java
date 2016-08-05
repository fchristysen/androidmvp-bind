package org.greenfroyo.androidmvp_bind.app._core.toolbar;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app._core.BaseDialog;
import org.greenfroyo.androidmvp_bind.app.login.LoginDialog;
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
        mToolbarBinding.setViewModel(getViewModel());
        setSupportActionBar(mToolbarBinding.toolbar);
        T binding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, null, false);
        mToolbarBinding.toolbarContent.addView(binding.getRoot());
        return binding;
    }

    @Override
    protected void onViewModelChanged(Observable observable, int i) {
        super.onViewModelChanged(observable, i);
        if(i == BR.login){
            invalidateOptionsMenu();
        }else if(i == BR.openLoginDialog){
            LoginDialog dialog = new LoginDialog(this);
            dialog.setDialogListener(new BaseDialog.DialogListener() {
                @Override
                public void onComplete(Dialog dialog) {
                    Log.d("login", "complete");
                }

                @Override
                public void onCancel(Dialog dialog) {
                    Log.d("login", "cancel");
                }

                @Override
                public void onDismiss(Dialog dialog) {
                    Log.d("login", "dismiss");
                }
            });
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(R.string.menu_show_activity_count);
        menu.add(R.string.menu_restart_activity_count);
        menu.add(R.string.menu_switch_locale);
        if(getViewModel().isLogin()) {
            menu.add(R.string.menu_sign_out);
        }else{
            menu.add(R.string.menu_sign_in);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().toString().equals(getContext().getString(R.string.menu_show_activity_count))){
            getPresenter().showActivityCount();
        }else if(item.getTitle().toString().equals(getContext().getString(R.string.menu_restart_activity_count))){
            getPresenter().resetActivityCount();
        }else if(item.getTitle().toString().equals(getContext().getString(R.string.menu_switch_locale))){
            getPresenter().switchLocale();
        }else if(item.getTitle().toString().equals(getContext().getString(R.string.menu_sign_in))){
            getPresenter().openLoginDialog();
        }else if(item.getTitle().toString().equals(getContext().getString(R.string.menu_sign_out))){
            getPresenter().signOut();
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
