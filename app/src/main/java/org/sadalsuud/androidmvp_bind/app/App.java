package org.sadalsuud.androidmvp_bind.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by fchristysen on 6/7/16.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        initStetho();
    }


    //region init tools
    private void initStetho(){
        Stetho.initializeWithDefaults(this);
    }
    //endregion


}
