package org.greenfroyo.androidmvp_bind.app.strip;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;

/**
 * Created by fchristysen on 8/5/16.
 */

public class StripPresenter extends BasePresenter<StripViewModel> {
    @Override
    public StripViewModel onCreateViewModel() {
        return new StripViewModel();
    }
}
