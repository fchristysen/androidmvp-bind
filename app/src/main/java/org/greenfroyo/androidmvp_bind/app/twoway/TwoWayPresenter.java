package org.greenfroyo.androidmvp_bind.app.twoway;

import org.greenfroyo.androidmvp_bind.app._core.BasePresenter;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayPresenter extends BasePresenter<TwoWayViewModel>{


    @Override
    public TwoWayViewModel onInitViewModel() {
        return new TwoWayViewModel();
    }
}
