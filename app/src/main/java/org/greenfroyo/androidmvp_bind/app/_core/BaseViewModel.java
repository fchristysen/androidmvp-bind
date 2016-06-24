package org.greenfroyo.androidmvp_bind.app._core;

import android.databinding.BaseObservable;

import org.greenfroyo.androidmvp_bind.framework.model.MvpViewModel;
import org.greenrobot.eventbus.EventBus;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 6/7/16.
 */

@Parcel
public abstract class BaseViewModel extends BaseObservable implements MvpViewModel {
    private EventBus mEvent = new EventBus();

    public EventBus getEventBus() {
        return mEvent;
    }

    public void subscribe(Object subscriber){
        mEvent.register(subscriber);
    }

    public static class SnackbarEvent{
        private String mMessage;

        public SnackbarEvent(String message) {
            mMessage = message;
        }

        public String getMessage() {
            return mMessage;
        }
    }
}
