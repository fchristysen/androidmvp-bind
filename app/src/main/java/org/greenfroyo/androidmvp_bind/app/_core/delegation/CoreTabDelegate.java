package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.greenfroyo.androidmvp_bind.R;

/**
 * Created by junius.ang on 8/24/2016.
 */
public class CoreTabDelegate<T extends CoreDelegateDependency> extends CoreActivityDelegate<T>{
    protected TabLayout vTabLayout;

    public CoreTabDelegate(T mCoreDelegateDependency) {
        super(mCoreDelegateDependency);
        getLayoutInflater().inflate(R.layout.layer_core_tab, getCoreDelegateDependency().getAppBarLayout(), true);
        vTabLayout = (TabLayout) getCoreDelegateDependency().getAppBarLayout().findViewById(R.id.core_tab);
    }
    //    public CoreTabDelegate(Context mContext) {
//        super(mContext);
//        ((AppCompatActivity)mContext).getLayoutInflater().inflate(R.layout.layer_core_fab, getCoordinatorLayout(), true);
//    }


    public TabLayout getTabLayout() {
        return vTabLayout;
    }

    /**
     *
     * @param scrollMode TabLayout.MODE_SCROLLABLE || TabLayout.MODE_FIXED
     */
    public void setScrollMode(@TabLayout.Mode int scrollMode){
        vTabLayout.setTabMode(scrollMode);
    }
    public void createDummyTab(int count){
        for(int x = 0; x< 5 ; x++) {
            vTabLayout.addTab(vTabLayout.newTab().setText("Tab "+(x+1)));
        }
    }
}
