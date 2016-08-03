package org.greenfroyo.androidmvp_bind.app.twoway;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayPresenter extends BaseToolbarPresenter<TwoWayViewModel> {


    @Override
    public TwoWayViewModel onCreateViewModel() {
        return new TwoWayViewModel();
    }
}
