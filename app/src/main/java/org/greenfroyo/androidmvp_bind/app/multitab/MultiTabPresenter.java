package org.greenfroyo.androidmvp_bind.app.multitab;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;

/**
 * Created by fchristysen on 6/30/16.
 */

public class MultiTabPresenter extends BasePresenter<MultiTabViewModel> {

    @Override
    public MultiTabViewModel onCreateViewModel() {
        return new MultiTabViewModel();
    }
}
