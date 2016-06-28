package org.greenfroyo.androidmvp_bind.app.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.App;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.domain.Home;
import org.greenfroyo.androidmvp_bind.provider.HomeProvider;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomePresenter extends BasePresenter<HomeViewModel> {

    //Provider
    private HomeProvider mHomeProvider;

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        mHomeProvider = new HomeProvider(App.getContext());
    }

    @Override
    public HomeViewModel onInitViewModel() {
        HomeViewModel model = new HomeViewModel();
        return model;
    }

    //region actionable
    public void refreshList() {
        getViewModel().setPageState(HomeViewModel.STATE_LOADING);

        if (Home.isAllowedToShow()) {
            getViewModel().clearContent();
            mHomeProvider.getMenuItems().subscribe(s -> {
                getViewModel().addContent(new HomeItemViewModel(s));
            }, error -> {
                getViewModel().setPageState(HomeViewModel.STATE_ERROR);
                getViewModel().getEventBus().post(new BaseViewModel.SnackbarEvent(App.resources().getString(R.string.home_title_error_message)));
            }, () -> {
                getViewModel().setPageState(HomeViewModel.STATE_SHOW);
            });
        } else {
            getViewModel().setPageState(HomeViewModel.STATE_ERROR);
            getViewModel().getEventBus().post(new BaseViewModel.SnackbarEvent(App.resources().getString(R.string.home_title_error_message)));
        }
    }

    public void openPage(Context context, Class pageClass) {
        Intent intent = new Intent(context, pageClass);
        context.startActivity(intent);
    }
    //region end
}
