package org.greenfroyo.androidmvp_bind.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.facebook.stetho.Stetho;

import org.greenfroyo.androidmvp_bind.driver.api.RxVolleyAPI;
import org.greenfroyo.androidmvp_bind.driver.database.ResolverDatabase;
import org.greenfroyo.androidmvp_bind.driver.preferences.MVPBPreference;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

/**
 * Created by fchristysen on 6/7/16.
 */

public class MVPBApp extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        BaseProvider.init(this
                , new MVPBPreference(this)
                , new ResolverDatabase(this)
                , new RxVolleyAPI(this));

        initStetho();
    }

    //region init tools
    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }
    //endregion

    public static Resources resources() {
        return sContext.getResources();
    }

    public static Context context() {
        return sContext;
    }
}
