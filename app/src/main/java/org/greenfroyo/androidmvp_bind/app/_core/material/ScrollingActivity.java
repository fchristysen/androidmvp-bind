package org.greenfroyo.androidmvp_bind.app._core.material;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.behavior.ScrollAwareFABBehavior;
import org.greenfroyo.androidmvp_bind.app._core.delegation.CoreFABDelegate;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        CoreFABDelegate coreFABDelegate = CoreFABDelegate.createFullImpl(this, getIntent(), -1, -1, Gravity.LEFT|Gravity.BOTTOM, null);
        CoreFABDelegate coreFABDelegate = CoreFABDelegate.createDefaultFABImpl(this);
        coreFABDelegate.setFABListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDetail);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 7"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 8"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 9"));
    }
}
