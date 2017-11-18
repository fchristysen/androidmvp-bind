package org.greenfroyo.androidmvp_bind.driver.preferences;

/**
 * Created by fchristysen on 7/27/16.
 */

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.Map;

/**
 * Created by fchristysen on 7/19/16.
 */

public class MVPBPreference implements PreferenceDriver {
    private Context mContext;

    public MVPBPreference(Context context) {
        this.mContext = context;
    }

    @Override
    public SharedPreferences open(String name) {
        return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    //================================================================================
    // Put
    //================================================================================

    @Override
    public boolean put(String prefName, String key, Object value) {
        SharedPreferences pref = open(prefName);
        if (pref != null) {
            SharedPreferences.Editor editor = pref.edit();
            put(editor, key, value);
            return editor.commit();
        }
        return false;
    }

    @Override
    public boolean put(String prefName, Map<String, Object> values) {
        SharedPreferences pref = open(prefName);
        if (pref != null) {
            SharedPreferences.Editor editor = pref.edit();
            for (Map.Entry<String, Object> value : values.entrySet()) {
                put(editor, value.getKey(), value.getValue());
            }
            return editor.commit();
        }
        return false;
    }

    private void put(SharedPreferences.Editor editor, String key, Object value) {
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
    }

    //================================================================================
    // Get
    //================================================================================

    @Override
    public boolean getBoolean(String prefName, String key, boolean defaultValue) {
        SharedPreferences pref = open(prefName);
        return pref != null && pref.getBoolean(key, defaultValue);
    }

    @Override
    public int getInteger(String prefName, String key, int defaultValue) {
        SharedPreferences pref = open(prefName);
        return pref != null ? pref.getInt(key, defaultValue) : defaultValue;
    }

    @Override
    public float getFloat(String prefName, String key, float defaultValue) {
        SharedPreferences pref = open(prefName);
        return pref != null ? pref.getFloat(key, defaultValue) : defaultValue;
    }

    @Override
    public long getLong(String prefName, String key, long defaultValue) {
        SharedPreferences pref = open(prefName);
        return pref != null ? pref.getLong(key, defaultValue) : defaultValue;
    }

    @Override
    public String getString(String prefName, String key, String defaultValue) {
        SharedPreferences pref = open(prefName);
        return pref != null ? pref.getString(key, defaultValue) : defaultValue;
    }

    //================================================================================
    // Delete
    //================================================================================

    @Override
    public boolean delete(String prefName, String key) {
        SharedPreferences pref = open(prefName);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        return editor.commit();
    }

    @Override
    public boolean delete(String prefName, List<String> keys) {
        SharedPreferences pref = open(prefName);
        SharedPreferences.Editor editor = pref.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        return editor.commit();
    }
}

