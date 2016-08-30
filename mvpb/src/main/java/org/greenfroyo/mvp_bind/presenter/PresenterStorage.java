package org.greenfroyo.mvp_bind.presenter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.repacked.antlr.v4.runtime.misc.Nullable;

import org.greenfroyo.mvp_bind.util.AppUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by fchristysen on 5/20/16.
 */
public class PresenterStorage {
    private static final String TAG = PresenterStorage.class.getSimpleName();
    private static final TimeUnit EXPIRATION_UNIT = TimeUnit.SECONDS;
    private static final long EXPIRATION = Long.MAX_VALUE;
    private static final long MAX_ENTRY = 100;

    private static PresenterStorage instance;

    private final Cache<String, MvpPresenter<?>> mPresenters;

    private PresenterStorage(long maxEntry, long expirationSeconds){
        mPresenters = CacheBuilder.newBuilder()
                .maximumSize(maxEntry)
                .expireAfterWrite(expirationSeconds, EXPIRATION_UNIT)
                .build();
    }

    public static PresenterStorage getInstance(){
        if(instance == null){
            instance = new PresenterStorage(MAX_ENTRY, EXPIRATION);
        }
        return instance;
    }

    @Nullable
    public <P extends MvpPresenter<?>> P get(String presenterId){
        P presenter = null;
        try {
            presenter = (P)mPresenters.getIfPresent(presenterId);
        }catch(ClassCastException e){
            e.printStackTrace();
        }
        return presenter;
    }

    public void add(final MvpPresenter<?> presenter){
        mPresenters.put(presenter.getID(), presenter);
        presenter.addOnDestroyListener(new MvpPresenter.OnDestroyListener() {
            @Override
            public void onDestroy(String presenterId) {
                mPresenters.invalidate(presenterId);
            }
        });
    }

}
