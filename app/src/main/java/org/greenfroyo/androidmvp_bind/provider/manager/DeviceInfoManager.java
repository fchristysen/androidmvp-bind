package org.greenfroyo.androidmvp_bind.provider.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.provider.common.LocalBroadcastBus;
import org.greenfroyo.androidmvp_bind.driver.DeviceInfoDriver;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

import java.util.Locale;

import rx.Observable;

/**
 * Created by fchristysen on 7/27/16.
 */

public class DeviceInfoManager extends BaseProvider {

    private static final String EVENT_SET_LOCALE = "org.greenfroyo.androidmvp_bind.set_locale";
    private static final String SET_LOCALE_NEW_COUNTRY = "org.greenfroyo.androidmvp_bind.set_locale.new_country";
    private static final String SET_LOCALE_NEW_LANGUAGE = "org.greenfroyo.androidmvp_bind.set_locale.new_language";

    public DeviceInfoManager() {
    }

    public Observable<DeviceInfoDataModel> getDeviceInfo() {
        DeviceInfoDriver driver = new DeviceInfoDriver();
        return Observable.zip(
                driver.getBuildInfo(), driver.getConfigInfo()
                , (build, config) -> {
                    DeviceInfoDataModel dataModel = new DeviceInfoDataModel();
                    dataModel.id = build.id;
                    dataModel.brand = build.brand;
                    dataModel.device = build.device;
                    dataModel.model = build.model;
                    dataModel.locale = config.locale;
                    return dataModel;
                });
    }

    public void setLocale(Locale locale){
        Configuration config = MVPBApp.resources().getConfiguration();
        config.locale = locale;
        MVPBApp.resources().updateConfiguration(config, MVPBApp.resources().getDisplayMetrics());
        Bundle extras = new Bundle();
        extras.putString(SET_LOCALE_NEW_COUNTRY, locale.getCountry());
        extras.putString(SET_LOCALE_NEW_LANGUAGE, locale.getLanguage());
        LocalBroadcastBus.get().send(EVENT_SET_LOCALE, extras);
    }

    /**
     * @return current state of locale
     */
    public Locale getLocale(){
        Configuration config = MVPBApp.resources().getConfiguration();
        return config.locale;
    }

    /**
     * @return observable which will notify on each config changes
     */
    public Observable<Locale> getLocaleStream(){
        return Observable.create(subscriber -> {
            subscriber.onNext(getLocale());
            LocalBroadcastBus.get().register(EVENT_SET_LOCALE, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle bundle = intent.getBundleExtra(LocalBroadcastBus.INTENT_BUNDLE);
                    String language = bundle.getString(SET_LOCALE_NEW_LANGUAGE);
                    String country = bundle.getString(SET_LOCALE_NEW_COUNTRY);
                    subscriber.onNext(new Locale(language, country));
                }
            });
        });
    }


}
