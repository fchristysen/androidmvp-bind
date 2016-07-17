package org.greenfroyo.androidmvp_bind.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.facebook.stetho.Stetho;

/**
 * Created by fchristysen on 6/7/16.
 */

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initStetho();
    }

    //region init tools
    private void initStetho(){
        Stetho.initializeWithDefaults(this);
    }
    //endregion

    public static Resources resources(){
        return mContext.getResources();
    }

    public static Context context() {
        return mContext;
    }
}
