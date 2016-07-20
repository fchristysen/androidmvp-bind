package org.greenfroyo.androidmvp_bind.driver;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fchristysen on 7/19/16.
 */

public class SharedPreferenceDriver {
    private Context mContext;
    private String mName;
    private WeakReference<SharedPreferences> mPreferences;

    public SharedPreferenceDriver(Context context, String name) {
        this.mContext = context;
        this.mName = name;
    }

    private SharedPreferences getPreferences() {
        if (mPreferences.get() == null) {
            mPreferences = new WeakReference<SharedPreferences>(mContext.getSharedPreferences(mName, Context.MODE_PRIVATE));
        }
        return mPreferences.get();
    }

    //================================================================================
    // Write
    //================================================================================

    private void write(String key, Object value) {
        SharedPreferences.Editor editor = mPreferences.get().edit();
        if (editor != null) {
            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof String) {
                editor.putString(key, String.valueOf(value));
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Double) {
                editor.putFloat(key, ((Double) value).floatValue());
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            }
        }
        editor.commit();
    }

    //================================================================================
    // Read
    //================================================================================

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return mPreferences.get() != null && mPreferences.get().getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return mPreferences.get() != null ? mPreferences.get().getString(key, defaultValue) : defaultValue;
    }

    public Integer getInteger(String key, Integer defaultValue) {
        return mPreferences.get() != null ? mPreferences.get().getInt(key, defaultValue) : defaultValue;
    }

    public Float getFloat(String key, Float defaultValue) {
        return mPreferences.get() != null ? mPreferences.get().getFloat(key, defaultValue) : defaultValue;
    }

    public Long getLong(String key, Long defaultValue) {
        return mPreferences.get() != null ? mPreferences.get().getLong(key, defaultValue) : defaultValue;
    }

    public Map<String, Object> get(Map<String, Object> keyAndDefault) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : keyAndDefault.entrySet()) {
            Object entryResult = null;
            Object defaultValue = entry.getValue();
            if (defaultValue instanceof String) {
                entryResult = getString(entry.getKey(), (String) defaultValue);
            } else if (defaultValue instanceof Boolean) {
                entryResult = getBoolean(entry.getKey(), (Boolean) defaultValue);
            } else if (defaultValue instanceof Integer) {
                entryResult = getInteger(entry.getKey(), (Integer) defaultValue);
            } else if (defaultValue instanceof Float) {
                entryResult = getFloat(entry.getKey(), (Float) defaultValue);
            } else if (defaultValue instanceof Long) {
                entryResult = getLong(entry.getKey(), (Long) defaultValue);
            }

            result.put(entry.getKey(), entryResult);
        }
        return result;
    }

    //================================================================================
    // Delete
    //================================================================================

    public boolean delete(String key) {
        SharedPreferences.Editor editor = mPreferences.get().edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean delete(List<String> keys) {
        SharedPreferences.Editor editor = mPreferences.get().edit();
        for (String key : keys) {
            editor.remove(key);
        }
        return editor.commit();
    }
}
