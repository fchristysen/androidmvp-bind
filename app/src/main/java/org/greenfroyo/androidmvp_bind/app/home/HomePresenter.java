package org.greenfroyo.androidmvp_bind.app.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenfroyo.androidmvp_bind.app.App;
import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
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
        super.onCreate(presenterState);
        mHomeProvider = new HomeProvider(App.getContext());
    }

    @Override
    public HomeViewModel onInitViewModel() {
        HomeViewModel model = new HomeViewModel();
        return model;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    //region actionable
    public void refreshList(){
        getViewModel().setPageState(HomeViewModel.STATE_LOADING);

        if(Home.isAllowedToShow()){
            getViewModel().clearContent();
            mHomeProvider.getMenuItems().subscribe(s -> {
                getViewModel().addContent(new HomeItemViewModel(s));
            }, error -> {
                getViewModel().setPageState(HomeViewModel.STATE_ERROR);
            }, () -> {
                getViewModel().setPageState(HomeViewModel.STATE_SHOW);
            });
        }else{
            getViewModel().setPageState(HomeViewModel.STATE_ERROR);
        }
    }
    //region end
}
