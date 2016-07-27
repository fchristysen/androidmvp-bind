package org.greenfroyo.androidmvp_bind.manager.device;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.driver.DeviceInfoDriver;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

import rx.Observable;

/**
 * Created by fchristysen on 7/27/16.
 */

public class DeviceInfoManager extends BaseProvider {

    public DeviceInfoManager(Context context) {
        super(context);
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
}
