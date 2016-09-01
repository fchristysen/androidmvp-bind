package org.greenfroyo.mvpb.presenter;

import com.google.repacked.antlr.v4.runtime.misc.Nullable;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.ValueSupplier;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.expiry.Expiry;

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

    private final CacheManager mCacheManager;
    private final Cache<String, MvpPresenter> mPresenters;

    private PresenterStorage(long maxEntry, long expirationSeconds){
        mCacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .build(true);

        mPresenters = mCacheManager.createCache("presenterStorage",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, MvpPresenter.class,
                        ResourcePoolsBuilder.heap(maxEntry))
                        .withExpiry(Expirations.timeToLiveExpiration(new Duration(expirationSeconds, EXPIRATION_UNIT))).build());

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
            presenter = (P)mPresenters.get(presenterId);
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
                mPresenters.remove(presenterId);
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mCacheManager.close();
    }
}
