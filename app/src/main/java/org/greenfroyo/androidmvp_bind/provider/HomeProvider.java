package org.greenfroyo.androidmvp_bind.provider;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.app.intentparam.IntentParamActivity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomeProvider extends BaseProvider {

    public static final String PAGE_INTENT_PARAM = IntentParamActivity.class.getSimpleName();

    public HomeProvider(Context context){
        super(context);
    }

    public Observable<String> getMenuItems(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(PAGE_INTENT_PARAM);
                subscriber.onCompleted();
            }
        });
    }
}
