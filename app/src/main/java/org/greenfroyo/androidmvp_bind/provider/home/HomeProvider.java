package org.greenfroyo.androidmvp_bind.provider.home;

import org.greenfroyo.androidmvp_bind.provider.common.CommonTransformer;
import org.greenfroyo.androidmvp_bind.app.compoundview.CompoundViewActivity;
import org.greenfroyo.androidmvp_bind.app.intentparam.front.IntentParamFrontActivity;
import org.greenfroyo.androidmvp_bind.app.login.LoginActivity;
import org.greenfroyo.androidmvp_bind.app.multitab.MultiTabActivity;
import org.greenfroyo.androidmvp_bind.app.twoway.TwoWayActivity;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

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
            , MultiTabActivity.class
    };

    public HomeProvider(){
    }

    public Observable<MainMenuDataModel> getMenuItems(){
        return Observable.from(PAGES)
                .map(next -> {
                    try{
                        Thread.sleep(3000/PAGES.length);
                    }catch (InterruptedException e){}
                    return new MainMenuDataModel(next);
                })
                .compose(CommonTransformer.doOnIOThread());
    }
}
