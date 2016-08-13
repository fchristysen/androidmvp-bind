package org.greenfroyo.androidmvp_bind.app.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenfroyo.androidmvp_bind.BR;

import java.util.List;

/**
 * Created by fchristysen on 6/24/16.
 */

public abstract class BindAdapter<T> extends RecyclerView.Adapter<BindAdapter.BindViewHolder>{
    private Context mContext;
    private List<T> mDataSet;
    private OnRecyclerItemClickListener<T> mListener;

    public BindAdapter(Context context) {
        this.mContext = context;
    }

    public Context getContext(){
        return mContext;
    }

    @Override
    public int getItemCount() {
        return mDataSet != null ? mDataSet.size() : 0;
    }

    public void setDataSet(List<T> dataSet){
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    public List getDataSet(){
        return mDataSet;
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
    public void onBindViewHolder(BindAdapter.BindViewHolder holder, int position) {
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

    protected static class BindViewHolder extends RecyclerView.ViewHolder{
        public BindViewHolder(View itemView){
            super(itemView);
        }

        public ViewDataBinding getBinding(){
            return DataBindingUtil.getBinding(itemView);
        }
    }
}
