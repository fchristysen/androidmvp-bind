package org.greenfroyo.androidmvp_bind.provider.log;

import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

/**
 * Created by fchristysen on 7/26/16.
 */

public class LogProvider extends BaseProvider {
    private static final String PREF_USAGE = "pref.usage";
    private static final String PREF_USAGE_ACTIVITY_COUNT = "activity_count";

    private static Integer mActivityCount;
    private static Integer mStoredActivityCount;

    public LogProvider() {
    }

    public Integer getActivityCount() {
        if (mActivityCount == null) {
            mActivityCount = new Integer(0);
        }
        return mActivityCount;
    }

    public void setActivityCount(Integer activityCount) {
        mActivityCount = activityCount;
    }

    public Integer getStoredActivityCount() {
        if (mStoredActivityCount == null) {
            mStoredActivityCount = getPreferenceDriver().getInteger(PREF_USAGE, PREF_USAGE_ACTIVITY_COUNT, 0);
        }
        return mStoredActivityCount;
    }

    public void setStoredActivityCount(Integer storedActivityCount) {
        mStoredActivityCount = storedActivityCount;
        getPreferenceDriver().put(PREF_USAGE, PREF_USAGE_ACTIVITY_COUNT, mStoredActivityCount);
    }
}
