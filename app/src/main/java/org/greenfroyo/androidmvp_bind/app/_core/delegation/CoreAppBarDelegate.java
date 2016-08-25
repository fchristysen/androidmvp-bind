package org.greenfroyo.androidmvp_bind.app._core.delegation;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by junius.ang on 8/24/2016.
 */
public class CoreAppBarDelegate<T extends CoreDelegateDependency> extends CoreActivityDelegate<T> {
    protected ImageView vToolBarImage;

    public CoreAppBarDelegate(T mCoreDelegateDependency, ImageView vToolBarImage) {
        super(mCoreDelegateDependency);
        this.vToolBarImage = vToolBarImage;
    }

    public ImageView getToolBarImageView(){
        return vToolBarImage;
    }
}
