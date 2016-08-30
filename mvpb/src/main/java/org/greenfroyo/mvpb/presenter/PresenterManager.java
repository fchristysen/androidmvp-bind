package org.greenfroyo.mvpb.presenter;

import android.os.Bundle;


/**
 * Created by fchristysen on 5/20/16.
 * This class purpose is to receive activity's lifecycle and then manage the presenter
 */
public class PresenterManager<P extends MvpPresenter> {
    public static final String KEY_PRESENTER_STATE = "presenter_state";
    public static final String KEY_PRESENTER_ID = "presenter_id";     //included in presenter bundle

    protected PresenterFactory<P> mPresenterFactory;
    protected P mPresenter;
    protected Bundle mPresenterBundle;

    public PresenterManager(PresenterFactory<P> presenterFactory){
        this.mPresenterFactory = presenterFactory;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        if(savedInstanceState!=null && savedInstanceState.containsKey(KEY_PRESENTER_STATE)){
            this.mPresenterBundle = savedInstanceState.getBundle(KEY_PRESENTER_STATE);
        }
    }

    public void onAttachedView(){
        getPresenter();
        if (mPresenter != null)
            mPresenter.attachView();
    }

    public void onDetachedView(boolean isFinishing){
        if (mPresenter != null) {
            mPresenter.detachView();
            if (isFinishing) {
                destroyPresenter();
            }
        }
    }

    public void destroyPresenter(){
        mPresenter.destroy();
    }

    /**
     * @param outState activity's bundle
     */
    public void onSaveInstanceState(Bundle outState){
        if(mPresenter != null){
            Bundle presenterBundle = new Bundle();
            mPresenter.saveInstanceState(presenterBundle);
            presenterBundle.putString(KEY_PRESENTER_ID, mPresenter.getID());
            outState.putBundle(KEY_PRESENTER_STATE, presenterBundle);
        }
    }

    /**
     * This will return non-null value. Presenter will be created based on previous savedState(if applicable)
     * @return Presenter
     */
    public P getPresenter(){
        if(mPresenter == null){
            if(mPresenterBundle != null){   //try to get presenter from cache
                mPresenter = PresenterStorage.getInstance().get(mPresenterBundle.getString(KEY_PRESENTER_ID));
            }
            if(mPresenter == null){     //recreate presenter if not exist in cache
                mPresenter = mPresenterFactory.createPresenter();
                mPresenter.create(mPresenterBundle);
                PresenterStorage.getInstance().add(mPresenter);
            }
        }
        return mPresenter;
    }
}
