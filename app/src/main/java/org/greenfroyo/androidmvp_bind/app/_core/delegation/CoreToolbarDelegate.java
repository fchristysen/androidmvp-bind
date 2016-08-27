package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.view.View;

/**
 * Created by junius.ang on 8/24/2016.
 */
public class CoreToolbarDelegate<T extends CoreDelegateDependency> extends CoreDelegate<T> {
    public CoreToolbarDelegate(T mCoreDelegateDependency) {
        super(mCoreDelegateDependency);
    }

    public void addView(View v, int visibility){

    }

}
