package org.greenfroyo.androidmvp_bind.provider;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.app.common.CommonTransformer;
import org.greenfroyo.androidmvp_bind.app.intentparam.front.IntentParamFrontActivity;
import org.greenfroyo.androidmvp_bind.app.twoway.TwoWayActivity;

import rx.Observable;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomeProvider extends BaseProvider {

    public static final Class PAGE_INTENT_PARAM = IntentParamFrontActivity.class;
    public static final Class PAGE_TWO_WAY = TwoWayActivity.class;

    public HomeProvider(Context context){
        super(context);
    }

    public Observable<Class> getMenuItems(){
        Class[] mPages = new Class[]{PAGE_INTENT_PARAM, PAGE_TWO_WAY};
        return Observable.from(mPages)
                .map(next -> {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){};
                    return next;
                })
                .compose(CommonTransformer.toIOThread());
    }
}
