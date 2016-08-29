package org.greenfroyo.androidmvp_bind.app.multitab.lorem;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;

/**
 * Created by fchristysen on 6/30/16.
 */

public class LoremPresenter extends BasePresenter<LoremViewModel> {


    @Override
    public LoremViewModel onCreateViewModel() {
        return new LoremViewModel();
    }
}
