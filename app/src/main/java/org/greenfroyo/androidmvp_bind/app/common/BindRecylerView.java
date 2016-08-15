package org.greenfroyo.androidmvp_bind.app.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by fchristysen on 6/26/16.
 */
public class BindRecylerView extends RecyclerView {
    private List mPendingItems;

    public BindRecylerView(Context context) {
        super(context);
    }

    public BindRecylerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BindRecylerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param items
     */
    public void setBindItems(List items) {
        if (getAdapter() != null) {
            if (getAdapter() instanceof BindAdapter) {
                BindAdapter adapter = (BindAdapter) getAdapter();
                if (items.equals(adapter.getDataSet())) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.setDataSet(items);
                }
            }
        } else {
            mPendingItems = items;
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (mPendingItems != null) {
            if (getAdapter() instanceof BindAdapter) {
                ((BindAdapter) getAdapter()).setDataSet(mPendingItems);
                mPendingItems = null;
            }
        }
    }
}
