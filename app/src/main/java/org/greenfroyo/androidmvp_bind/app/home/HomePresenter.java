package org.greenfroyo.androidmvp_bind.app.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.app._core.error.BaseErrorPresenter;
import org.greenfroyo.androidmvp_bind.app._core.error.BaseErrorViewModel;
import org.greenfroyo.androidmvp_bind.app.intentparam.back.IntentParamBackActivity;
import org.greenfroyo.androidmvp_bind.app.intentparam.front.IntentParamFrontActivity;
import org.greenfroyo.androidmvp_bind.app.login.LoginDialog;
import org.greenfroyo.androidmvp_bind.bridge.HomeBridge;
import org.greenfroyo.androidmvp_bind.domain.Home;
import org.greenfroyo.androidmvp_bind.provider.home.HomeProvider;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomePresenter extends BaseErrorPresenter<HomeViewModel> {

    //Provider
    private HomeProvider mHomeProvider;

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
        mHomeProvider = new HomeProvider();
    }

    @Override
    public HomeViewModel onCreateViewModel() {
        HomeViewModel model = new HomeViewModel();
        return model;
    }

    //region actionable
    public void refreshList() {
        getViewModel().setPageState(HomeViewModel.STATE_LOADING);

        if (Home.isAllowedToShow()) {
            getViewModel().clearContent();
            mHomeProvider.getMenuItems().map(HomeBridge::getHomeItem).subscribe(next -> {
                getViewModel().addContent(next);
            }, error -> {
                showError(BaseErrorViewModel.ERROR_500);
                getViewModel().setToastMessage(MVPBApp.resources().getString(R.string.home_title_error_message));
            }, () -> {
                hideError();
                getViewModel().setPageState(HomeViewModel.STATE_SHOW);
            });
        } else {
            showError(BaseErrorViewModel.ERROR_500);
            getViewModel().setToastMessage(MVPBApp.resources().getString(R.string.home_title_error_message));
        }
    }

    public void openPage(Context context, Class pageClass) {
        Intent intent = new Intent(context, pageClass);
        context.startActivity(intent);
    }
    //region end
}
