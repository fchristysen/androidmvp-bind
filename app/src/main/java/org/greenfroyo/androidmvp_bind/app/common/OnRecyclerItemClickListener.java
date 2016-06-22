package org.greenfroyo.androidmvp_bind.app.common;

/**
 * Created by fchristysen on 6/22/16.
 */

public interface OnRecyclerItemClickListener<T> {
    public void onItemClick(int position, T item);
}
