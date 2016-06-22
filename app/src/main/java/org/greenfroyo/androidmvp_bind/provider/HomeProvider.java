package org.greenfroyo.androidmvp_bind.provider;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.app.intentparam.IntentParamFrontActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomeProvider extends BaseProvider {

    public static final String PAGE_INTENT_PARAM = IntentParamFrontActivity.class.getSimpleName();

    public HomeProvider(Context context){
        super(context);
    }

    public Observable<String> getMenuItems(){
        return Observable.<String>create(subscriber -> {
                for(int i=0;i<5;i++) {
                    subscriber.onNext(PAGE_INTENT_PARAM);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
                subscriber.onCompleted();
            }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
