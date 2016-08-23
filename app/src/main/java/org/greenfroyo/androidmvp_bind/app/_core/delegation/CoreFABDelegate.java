package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.behavior.ScrollAwareFABBehavior;

/**
 * Created by junius.ang on 8/23/2016.
 * must be used from V3 environment
 */
public class CoreFABDelegate extends CoreActivityDelegate {
    /**
     * This builder will create default implementation of FAB in bottom end gravity
     * @param context
     * @return CoreFABDelegate
     */
    public static CoreFABDelegate createDefaultFABImpl(Context context){
        CoreFABDelegate coreFABDelegate = new CoreFABDelegate(context);
        CoordinatorLayout.LayoutParams p = ((CoordinatorLayout.LayoutParams)coreFABDelegate.getFAB().getLayoutParams());
        p.gravity = Gravity.BOTTOM | Gravity.END;
        coreFABDelegate.getFAB().setLayoutParams(p);
        return coreFABDelegate;
    }

    /**
     * will call createFullImpl but with predefined value as this builder intended so FAB can stick to the AppBar
     * @param context
     * @return
     */
    public static CoreFABDelegate createAnchorAppBarFABImpl(Context context){
        return createFullImpl(context, R.id.core_app_bar, Gravity.BOTTOM | Gravity.END, -1, new ScrollAwareFABBehavior(context, null));
    }

    /**
     * Use this builder if you need to customize anchor and behavior
     * @param anchorId this attribute will determine to which view this FAB will be anchored to, set -1 to ignore
     * @param anchorGravity this attribute will determine the anchor position from the anchored view, set -1 to ignore
     * @param gravity this attribute is recessive with anchor gravity, if you set anchorGravity as -1, this gravity will be used instead (no anchoring provided), vice versa. -1 to ignore, do not set concurrent with anchorgravity!
     * @param behavior this will determine the behavior for FAB, currently only support ScrollAwareFAB behavior, set null to ignore
     * @return CoreFABDelegate
     */
    public static CoreFABDelegate createFullImpl(Context context, @IdRes int anchorId, int anchorGravity, int gravity, FloatingActionButton.Behavior behavior){
        CoreFABDelegate coreFABDelegate = new CoreFABDelegate(context);
        CoordinatorLayout.LayoutParams p = ((CoordinatorLayout.LayoutParams) coreFABDelegate.getFAB().getLayoutParams());
        if(anchorId != -1) {
            p.setAnchorId(anchorId);
            if(anchorGravity != -1){
                p.anchorGravity = anchorGravity;
            }
        }
        if(anchorGravity == -1 && gravity != -1){
            p.gravity = gravity;
        }
        if(behavior != null) {
            p.setBehavior(behavior);
        }
        coreFABDelegate.getFAB().setLayoutParams(p);
        return coreFABDelegate;
    }

    protected FloatingActionButton mFloatingActionButton;

    public CoreFABDelegate(Context mContext) {
        super(mContext);
        ((AppCompatActivity)mContext).getLayoutInflater().inflate(R.layout.layer_core_fab, mCoordinatorLayout, true);
        mFloatingActionButton = (FloatingActionButton) (mCoordinatorLayout.findViewById(R.id.core_fab));
    }

    /**
     * to set custom image for FAB
     * @param resId
     */
    public void setImageDrawable(int resId){
        mFloatingActionButton.setImageResource(resId);
    }

    /**
     * get FAB
     * @return
     */
    public FloatingActionButton getFAB(){
        return checkDelegateStatus() ? mFloatingActionButton : null;
    }

    public void setFABListener(View.OnClickListener onClickListener){
        getFAB().setOnClickListener(onClickListener);
    }

    /**
     * Behavior for scrolling behavior, will be automatically set by coordinator layout
     * @param behavior
     */
    public void setFABBehavior(FloatingActionButton.Behavior behavior){
        CoordinatorLayout.LayoutParams p = ((CoordinatorLayout.LayoutParams)getFAB().getLayoutParams());
        p.setBehavior(behavior);
        getFAB().setLayoutParams(p);
    }
}

/**
 * sample usage
 *
 CoreFABDelegate coreFABDelegate = CoreFABDelegate.createFullImpl(this, getIntent(), -1, -1, Gravity.LEFT|Gravity.BOTTOM, null);
 coreFABDelegate.setFABListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
});
 coreFABDelegate.setFABBehavior(new ScrollAwareFABBehavior(this, null));
 */
