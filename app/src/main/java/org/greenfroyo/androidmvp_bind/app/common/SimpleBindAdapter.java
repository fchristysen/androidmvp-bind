package org.greenfroyo.androidmvp_bind.app.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.home.HomeActivity;
import org.greenfroyo.androidmvp_bind.app.home.HomeItemViewModel;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

import java.util.List;

/**
 * Created by fchristysen on 6/24/16.
 */

public abstract class SimpleBindAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<SimpleBindAdapter.BindViewHolder<B>>{
    private Context mContext;
    private List<T> mDataSet;
    private OnRecyclerItemClickListener<T> mListener;

    public SimpleBindAdapter(Context context) {
        this.mContext = context;
    }

    public Context getContext(){
        return mContext;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setDataSet(List<T> dataSet){
        mDataSet = dataSet;
    }

    public void addItem(T item){
        mDataSet.add(item);
    }

    public T getItem(int position){
        return mDataSet.get(position);
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener<T> listener) {
        mListener = listener;
    }

    public OnRecyclerItemClickListener<T> getOnItemClickListener() {
        return mListener;
    }

    @Override
    public void onBindViewHolder(SimpleBindAdapter.BindViewHolder<B> holder, int position) {
        holder.getBinding().setVariable(BR.viewModel, mDataSet.get(position));
        holder.getBinding().executePendingBindings();
        if(mListener!=null){
            holder.getBinding().getRoot().setOnClickListener(v -> {
                mListener.onItemClick(position, mDataSet.get(position));
            });
        }else{
            holder.getBinding().getRoot().setOnClickListener(null);
        }
    }

    protected static class BindViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder{
        public BindViewHolder(View itemView){
            super(itemView);
        }

        public B getBinding(){
            return DataBindingUtil.getBinding(itemView);
        }
    }
}
