package org.greenfroyo.androidmvp_bind.provider.common;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fchristysen on 6/26/16.
 */
public class CommonTransformer{


    public static <T>Observable.Transformer<T, T> doOnIOThread(){
        return observable -> observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T>Observable.Transformer<T, T> doOnNewThread(){
        return observable -> observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
