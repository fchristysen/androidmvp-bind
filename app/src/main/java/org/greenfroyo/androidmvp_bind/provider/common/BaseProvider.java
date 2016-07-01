package org.greenfroyo.androidmvp_bind.provider.common;

import android.content.Context;

/**
 * Created by fchristysen on 6/7/16.
 */

public class BaseProvider {
    private Context mContext;

    public BaseProvider(Context context){
        mContext = context.getApplicationContext();
    }
}
