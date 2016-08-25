package org.greenfroyo.androidmvp_bind.app._core.material;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.behavior.ScrollAwareFABBehavior;
import org.greenfroyo.androidmvp_bind.app._core.delegation.CoreDelegateDependency;
import org.greenfroyo.androidmvp_bind.app._core.delegation.CoreFABDelegate;
import org.greenfroyo.androidmvp_bind.app._core.delegation.CoreTabDelegate;

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
        CoreDelegateDependency coreDelegateDependency = new CoreDelegateDependency(getLayoutInflater(), (CoordinatorLayout) findViewById(R.id.core_coordinator_layout), (AppBarLayout) findViewById(R.id.core_app_bar));
        CoreFABDelegate coreFABDelegate = CoreFABDelegate.createDefaultFABImpl(coreDelegateDependency);
        coreFABDelegate.setFABBehavior(new ScrollAwareFABBehavior());
        coreFABDelegate.setFABListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        CoreTabDelegate<CoreDelegateDependency> coreTabDelegate = new CoreTabDelegate<>(coreDelegateDependency);
        coreTabDelegate.setScrollMode(TabLayout.MODE_SCROLLABLE);
        coreTabDelegate.createDummyTab(5);

    }
}
