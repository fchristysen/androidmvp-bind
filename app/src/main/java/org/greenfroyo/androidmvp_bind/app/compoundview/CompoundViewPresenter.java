package org.greenfroyo.androidmvp_bind.app.compoundview;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.mvp_bind.model.MvpViewModel;

/**
 * Created by fchristysen on 6/27/16.
 */

public class CompoundViewPresenter extends BasePresenter<CompoundViewViewModel> {
    @Override
    public CompoundViewViewModel onInitViewModel() {
        CompoundViewViewModel model = new CompoundViewViewModel();
        return model;
    }
}
