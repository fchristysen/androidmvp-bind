package org.greenfroyo.mvp_bind.presenter;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;

import org.greenfroyo.mvp_bind.presenter.MvpPresenter;
import org.greenfroyo.mvp_bind.presenter.PresenterFactory;
import org.greenfroyo.mvp_bind.presenter.PresenterManager;
import org.greenfroyo.mvp_bind.view.MvpView;


/**
 * Created by fchristysen on 1/25/16.
 * This class encapsulate additional presenter management specifically for android compound view
 * Each compound view must pass lifecycle events to this presenter manager
 */
public class CompoundPresenterManager<P extends MvpPresenter>{
    public static final String KEY_PARENT_STATE = "parent_state";
    public static final String KEY_CHILDREN_STATE = "children_state";

    private PresenterManager<P> mPresenterManager;

    public CompoundPresenterManager(PresenterFactory<P> presenterFactory){
        mPresenterManager = new PresenterManager<>(presenterFactory);
    }

    /**
     * @see PresenterManager#onAttachedView(MvpView)
     */
    public void onAttachedView(MvpView view){
        mPresenterManager.onAttachedView(view);
    }

    /**
     * @see PresenterManager#onDetachedView(boolean isFinishing)
     */
    public void onDetachedView(boolean isFinishing){
        mPresenterManager.onDetachedView(isFinishing);
    }

    public Parcelable onRestoreInstanceState(ViewGroup viewGroup, Parcelable state){
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            for(int i=0;i<viewGroup.getChildCount();i++){
                viewGroup.getChildAt(i).restoreHierarchyState(bundle.getSparseParcelableArray(KEY_CHILDREN_STATE));
            }
            mPresenterManager.onRestoreInstanceState(bundle);
            return bundle.getParcelable(KEY_PARENT_STATE);
        }
        return state;
    }

    public Parcelable onSaveInstanceState(ViewGroup viewGroup, Parcelable outState){
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARENT_STATE, outState);
        SparseArray<Parcelable> childState = new SparseArray();
        for(int i=0;i<viewGroup.getChildCount();i++){
            viewGroup.getChildAt(i).saveHierarchyState(childState);
        }
        bundle.putSparseParcelableArray(KEY_CHILDREN_STATE, childState);
        mPresenterManager.onSaveInstanceState(bundle);
        return bundle;
    }

    /**
     * @see PresenterManager#getPresenter()
     */
    public P getPresenter(){
        return mPresenterManager.getPresenter();
    }
}

