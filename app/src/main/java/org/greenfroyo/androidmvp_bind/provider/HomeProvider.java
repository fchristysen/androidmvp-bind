package org.greenfroyo.androidmvp_bind.provider;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.app.common.CommonTransformer;
import org.greenfroyo.androidmvp_bind.app.compoundview.CompoundViewActivity;
import org.greenfroyo.androidmvp_bind.app.intentparam.front.IntentParamFrontActivity;
import org.greenfroyo.androidmvp_bind.app.login.LoginActivity;
import org.greenfroyo.androidmvp_bind.app.twoway.TwoWayActivity;

import rx.Observable;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomeProvider extends BaseProvider {

    public static final Class[] PAGES = new Class[]{
            IntentParamFrontActivity.class
            , TwoWayActivity.class
            , CompoundViewActivity.class
            , LoginActivity.class
    };

    public HomeProvider(Context context){
        super(context);
    }

    public Observable<Class> getMenuItems(){
        return Observable.from(PAGES)
                .map(next -> {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){};
                    return next;
                })
                .compose(CommonTransformer.toIOThread());
    }
}
