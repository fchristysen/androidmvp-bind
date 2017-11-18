package org.greenfroyo.androidmvp_bind.provider.common;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.driver.api.ApiDriver;
import org.greenfroyo.androidmvp_bind.driver.database.DatabaseDriver;
import org.greenfroyo.androidmvp_bind.driver.preferences.PreferenceDriver;

/**
 * Created by fchristysen on 6/7/16.
 */

public class BaseProvider {
    private static Context sContext;
    private static PreferenceDriver sSharedPreferenceDriver;
    private static DatabaseDriver sDatabaseDriver;
    private static ApiDriver sAPIDriver;

    public static void init(Context context, PreferenceDriver sp, DatabaseDriver db, ApiDriver api){
        BaseProvider.sContext = context.getApplicationContext();
        BaseProvider.sSharedPreferenceDriver = sp;
        BaseProvider.sDatabaseDriver = db;
        BaseProvider.sAPIDriver = api;
    }

    protected Context getContext(){
        return BaseProvider.sContext;
    }

    protected PreferenceDriver getPreferenceDriver(){
        return BaseProvider.sSharedPreferenceDriver;
    }

    protected ApiDriver getAPIDriver(){
        return sAPIDriver;
    }

}
