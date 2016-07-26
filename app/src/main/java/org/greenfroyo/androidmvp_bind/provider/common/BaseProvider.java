package org.greenfroyo.androidmvp_bind.provider.common;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.driver.SharedPreferenceDriver;

import static org.greenfroyo.androidmvp_bind.provider.log.LogProvider.PREF_USAGE;
import static org.greenfroyo.androidmvp_bind.provider.log.LogProvider.PREF_USAGE_ACTIVITY_COUNT;

/**
 * Created by fchristysen on 6/7/16.
 */

public class BaseProvider {
    protected Context mContext;

    public BaseProvider(Context context){
        mContext = context.getApplicationContext();
    }
}
