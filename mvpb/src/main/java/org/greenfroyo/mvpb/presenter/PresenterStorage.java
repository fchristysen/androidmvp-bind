package org.greenfroyo.mvpb.presenter;


import android.util.LruCache;

/**
 * Created by fchristysen on 5/20/16.
 */
public class PresenterStorage {
    private static final String TAG = PresenterStorage.class.getSimpleName();
    private static final int MAX_ENTRY = 100;

    private static PresenterStorage instance;

    private final LruCache<String, MvpPresenter> mPresenters;

    private PresenterStorage(int maxEntry){
        mPresenters = new LruCache<>(maxEntry);

    }

    public static PresenterStorage getInstance(){
        if(instance == null){
            instance = new PresenterStorage(MAX_ENTRY);
        }
        return instance;
    }

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
        mPresenters.evictAll();
    }
}
