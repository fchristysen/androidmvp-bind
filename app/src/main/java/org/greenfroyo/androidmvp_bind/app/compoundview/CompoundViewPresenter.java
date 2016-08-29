package org.greenfroyo.androidmvp_bind.app.compoundview;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;

/**
 * Created by fchristysen on 6/27/16.
 */

public class CompoundViewPresenter extends BaseToolbarPresenter<CompoundViewViewModel> {
    @Override
    public CompoundViewViewModel onCreateViewModel() {
        CompoundViewViewModel model = new CompoundViewViewModel();
        return model;
    }
}
