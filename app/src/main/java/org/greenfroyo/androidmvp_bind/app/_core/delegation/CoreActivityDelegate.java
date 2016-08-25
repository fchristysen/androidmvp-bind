package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.greenfroyo.androidmvp_bind.R;

/**
 * Created by junius.ang on 8/23/2016.
 */
public abstract class CoreActivityDelegate<T extends CoreDelegateDependency>{
    T mCoreDelegateDependency;

    //temporary solution for appbar dependency
    public CoreActivityDelegate(T mCoreDelegateDependency){
        this.mCoreDelegateDependency = mCoreDelegateDependency;
    }

//    public abstract void onCreate(Bundle savedInstanceState);
//    public abstract void onStart();
//    public abstract void onResume();
//    public abstract void onStop();


    public void setCoreDelegateDependency(T mCoreDelegateDependency) {
        this.mCoreDelegateDependency = mCoreDelegateDependency;
    }

    public T getCoreDelegateDependency(){
        return mCoreDelegateDependency;
    }

    protected CoordinatorLayout getCoordinatorLayout(){
        return mCoreDelegateDependency.getCoordinatorLayout();
    }

    protected Context getContext(){
        return mCoreDelegateDependency.getContext();
    }
}
