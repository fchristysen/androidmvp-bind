package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.greenfroyo.androidmvp_bind.R;

/**
 * Created by junius.ang on 8/24/2016.
 */
public class CoreTabDelegate<T extends CoreDelegateDependency> extends CoreActivityDelegate<T>{
    protected Toolbar vToolbar;

    public CoreTabDelegate(T mCoreDelegateDependency) {
        super(mCoreDelegateDependency);
    }
    //    public CoreTabDelegate(Context mContext) {
//        super(mContext);
//        ((AppCompatActivity)mContext).getLayoutInflater().inflate(R.layout.layer_core_fab, getCoordinatorLayout(), true);
//    }


    public Toolbar getToolbar(){
        return vToolbar;
    }
}
