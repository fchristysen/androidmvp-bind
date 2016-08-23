package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.greenfroyo.androidmvp_bind.R;

/**
 * Created by junius.ang on 8/23/2016.
 */
public abstract class CoreActivityDelegate{
    protected Context mContext;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    protected boolean isDelegateDisabled;
    protected CoordinatorLayout mCoordinatorLayout;
    //temporary solution for appbar dependency
    public CoreActivityDelegate(Context mContext){
        this.mContext = mContext;
        findCoreView();
    }

//    public abstract void onCreate(Bundle savedInstanceState);
//    public abstract void onStart();
//    public abstract void onResume();
//    public abstract void onStop();

    protected void findCoreView(){
        AppCompatActivity activity = (AppCompatActivity) mContext;
        mCoordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.coordinator_layout);
        mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
    }

    public Toolbar getToolbar(){
        return mToolbar;
    }

    protected final boolean checkDelegateStatus(){
        if(isDelegateDisabled){
            throw new IllegalStateException("This delegate is disabled because the selected screen style does not accomodate it, please choose another");
        }
        return true;
    }
}
