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
import android.widget.TextView;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.databinding.HomeActivityBinding;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel>
        implements SwipeRefreshLayout.OnRefreshListener {

    //region Views
    private HomeActivityBinding mBinding;
    private TextView vHeader;
    private SwipeRefreshLayout vSwipe;
    private RecyclerView vContent;
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
    protected void onInitView(HomeViewModel viewModel) {
        super.onInitView(viewModel);
        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        vSwipe = mBinding.vgSwipe;
        vHeader = mBinding.tvHeader;
        vContent = mBinding.lvContent;
        mBinding.setHome(viewModel);
        //configure adapter
        BindArrayAdapter adp = new BindArrayAdapter();
        adp.setDataSet(viewModel.getContent());
        vContent.setHasFixedSize(true);
        vContent.setLayoutManager(new LinearLayoutManager(this));
        vContent.setAdapter(adp);
        viewModel.addOnPropertyChangedCallback(new ViewListener());
    }

    @Override
    protected void onInitListener() {
        super.onInitListener();
        vSwipe.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getPresenter().refreshList();
    }

    public class ViewListener extends Observable.OnPropertyChangedCallback{
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if(i == BR.content) {
                HomeActivity.this.vContent.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public static class BindArrayAdapter extends RecyclerView.Adapter<BindArrayAdapter.BindViewHolder>{
        private List<HomeItemViewModel> mDataSet;

        public BindArrayAdapter() {
        }

        @Override
        public BindArrayAdapter.BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            HomeListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_list_item, null, false);
            return new BindViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(BindArrayAdapter.BindViewHolder holder, int position) {
            holder.getBinding().setVariable(BR.item, mDataSet.get(position));
            holder.getBinding().executePendingBindings();
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
