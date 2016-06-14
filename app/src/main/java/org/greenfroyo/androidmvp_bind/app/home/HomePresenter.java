package org.greenfroyo.androidmvp_bind.app.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.domain.Home;
import org.greenfroyo.androidmvp_bind.provider.HomeProvider;

import java.util.ArrayList;

import rx.functions.Action1;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomePresenter extends BasePresenter<HomeViewModel> {

    //Provider
    private HomeProvider mHomeProvider;

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
    }

    @Override
    public HomeViewModel onInitViewModel() {
        HomeViewModel model = new HomeViewModel();
        model.setPageState(HomeViewModel.STATE_SHOW);
        model.setContent(new ArrayList<String>());
        return model;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        mHomeProvider = new HomeProvider(getView().getContext());

        if(Home.isAllowedToShow()){
            getViewModel().getContent().clear();
            mHomeProvider.getMenuItems().subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    getViewModel().getContent().add(s);
                    getView().onViewModelChanged(getViewModel());
                }
            });
        }
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
    }
}
