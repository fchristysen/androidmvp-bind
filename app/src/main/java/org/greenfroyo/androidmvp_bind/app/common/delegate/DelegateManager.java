package org.greenfroyo.androidmvp_bind.app.common.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fchristysen on 8/15/16.
 */

public class DelegateManager<T, VH extends RecyclerView.ViewHolder> {
    List<AdapterDelegate<T, VH>> mDelegates = new ArrayList<>();

    //return position of the added delegate, use this as item view type
    public int addDelegate(@NonNull AdapterDelegate<T, VH> delegate) {
        mDelegates.add(delegate);
        return mDelegates.indexOf(delegate);
    }

    public int getItemViewType(@NonNull T items, int position) {
        for(int i=0;i<mDelegates.size();i++){
            if(mDelegates.get(i).isForViewType(items, position)){
                return i;
            }
        }
        return -1;
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegates.get(viewType).onCreateViewHolder(parent);
    }

    public void onBindViewHolder(@NonNull T items, int position, @NonNull VH viewHolder) {
        mDelegates.get(getItemViewType(items, position)).onBindViewHolder(items, position, viewHolder);
    }
}
