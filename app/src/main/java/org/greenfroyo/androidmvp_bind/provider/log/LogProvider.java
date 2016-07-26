package org.greenfroyo.androidmvp_bind.provider.log;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.driver.SharedPreferenceDriver;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

/**
 * Created by fchristysen on 7/26/16.
 */

public class LogProvider extends BaseProvider{
    public static final String PREF_USAGE = "pref.usage";
    public static final String PREF_USAGE_ACTIVITY_COUNT = "activity_count";

    private static Integer mActivityCount;
    private static Integer mStoredActivityCount;

    private SharedPreferenceDriver mPreferenceDriver;

    public LogProvider(Context context) {
        super(context);
        mPreferenceDriver = new SharedPreferenceDriver(mContext, PREF_USAGE);
    }

    public Integer getActivityCount() {
        if(mActivityCount==null){
            mActivityCount = new Integer(0);
        }
        return mActivityCount;
    }

    public void setActivityCount(Integer activityCount) {
        mActivityCount = activityCount;
    }

    public Integer getStoredActivityCount() {
        if(mStoredActivityCount == null){
            mStoredActivityCount = mPreferenceDriver.getInteger(PREF_USAGE_ACTIVITY_COUNT, 0);
        }
        return mStoredActivityCount;
    }

    public void setStoredActivityCount(Integer storedActivityCount) {
        mStoredActivityCount = storedActivityCount;
        mPreferenceDriver.write(PREF_USAGE_ACTIVITY_COUNT, mStoredActivityCount);
    }
}
