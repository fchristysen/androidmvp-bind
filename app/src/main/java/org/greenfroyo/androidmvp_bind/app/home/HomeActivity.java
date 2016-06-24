package org.greenfroyo.androidmvp_bind.app.home;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app.common.OnRecyclerItemClickListener;
import org.greenfroyo.androidmvp_bind.databinding.HomeActivityBinding;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel>
        implements SwipeRefreshLayout.OnRefreshListener, OnRecyclerItemClickListener<HomeItemViewModel> {

    //region Views
    private HomeActivityBinding mBinding;
    private BindArrayAdapter mContentAdapter;
    //endregion

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ViewDataBinding onInitView(HomeViewModel viewModel) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        mBinding.setViewModel(viewModel);
        viewModel.addOnPropertyChangedCallback(new ViewListener());
        //configure adapter
        mContentAdapter = new BindArrayAdapter();
        mContentAdapter.setDataSet(viewModel.getContent());
        mBinding.lvContent.setLayoutManager(new LinearLayoutManager(this));
        mBinding.lvContent.setAdapter(mContentAdapter);
        return mBinding;
    }

    @Override
    protected void onInitListener() {
        super.onInitListener();
        mBinding.vgSwipe.setOnRefreshListener(this);
        mContentAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        getPresenter().refreshList();
    }

    @Override
    public void onItemClick(int position, HomeItemViewModel item) {
        getPresenter().openPage(this, item.getClassObject());
    }

    public class ViewListener extends Observable.OnPropertyChangedCallback{
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if(i == BR.content) {
                HomeActivity.this.mBinding.lvContent.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public static class BindArrayAdapter extends RecyclerView.Adapter<BindArrayAdapter.BindViewHolder>{
        private List<HomeItemViewModel> mDataSet;
        private OnRecyclerItemClickListener<HomeItemViewModel> mListener;

        public BindArrayAdapter() {
        }

        public void setOnItemClickListener(OnRecyclerItemClickListener<HomeItemViewModel> listener) {
            mListener = listener;
        }

        @Override
        public BindArrayAdapter.BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            HomeListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_list_item, null, false);
            return new BindViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(BindArrayAdapter.BindViewHolder holder, int position) {
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

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        public void setDataSet(List<HomeItemViewModel> dataSet){
            mDataSet = dataSet;
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
}
