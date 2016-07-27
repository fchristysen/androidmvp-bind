package org.greenfroyo.androidmvp_bind.driver;

import android.content.res.Configuration;
import android.os.Build;

import org.greenfroyo.androidmvp_bind.app.MVPBApp;

import java.util.Locale;

import rx.Observable;

/**
 * Created by fchristysen on 7/27/16.
 */

public class DeviceInfoDriver {

    public DeviceInfoDriver(){
    }

    public Observable<BuildInfo> getBuildInfo(){
        BuildInfo info = new BuildInfo();
        info.id = Build.ID;
        info.display = Build.DISPLAY;
        info.device = Build.DEVICE;
        info.manufacturer = Build.MANUFACTURER;
        info.brand = Build.BRAND;
        info.model = Build.MODEL;
        info.serial = Build.SERIAL;
        return Observable.just(info);
    }

    public Observable<ConfigInfo> getConfigInfo(){
        Configuration config = MVPBApp.context().getResources().getConfiguration();
        ConfigInfo info = new ConfigInfo();
        info.locale = config.locale;
        info.density = config.densityDpi;
        info.fontScale = config.fontScale;
        return Observable.just(info);
    }

    public static class BuildInfo{
        public String id;
        public String display;
        public String device;
        public String manufacturer;
        public String brand;
        public String model;
        public String serial;
    }

    public static class ConfigInfo{
        public Locale locale;
        public int density;
        public float fontScale;
    }
}
