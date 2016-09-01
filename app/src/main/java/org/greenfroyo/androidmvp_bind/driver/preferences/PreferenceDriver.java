package org.greenfroyo.androidmvp_bind.driver.preferences;

import android.content.SharedPreferences;

import java.util.List;
import java.util.Map;

/**
 * Created by fchristysen on 7/27/16.
 */
public interface PreferenceDriver {
    SharedPreferences open(String name);

    boolean put(String prefName, String key, Object value);

    boolean put(String prefName, Map<String, Object> values);

    boolean getBoolean(String prefName, String key, boolean defaultValue);

    int getInteger(String prefName, String key, int defaultValue);

    float getFloat(String prefName, String key, float defaultValue);

    long getLong(String prefName, String key, long defaultValue);

    String getString(String prefName, String key, String defaultValue);

    boolean delete(String prefName, String key);

    boolean delete(String prefName, List<String> keys);
}
