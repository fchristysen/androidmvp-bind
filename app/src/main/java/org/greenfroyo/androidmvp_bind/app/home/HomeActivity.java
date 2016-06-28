package org.greenfroyo.androidmvp_bind.app.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app.common.BindAdapter;
import org.greenfroyo.androidmvp_bind.app.common.OnRecyclerItemClickListener;
import org.greenfroyo.androidmvp_bind.databinding.HomeActivityBinding;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel>
        implements SwipeRefreshLayout.OnRefreshListener, OnRecyclerItemClickListener<HomeItemViewModel> {

    //region Views
    private HomeActivityBinding mBinding;
    private HomeAdapter mContentAdapter;
    //endregion

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected ViewDataBinding onInitView(HomeViewModel viewModel) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        mBinding.setViewModel(viewModel);

        //configure adapter
        mContentAdapter = new HomeAdapter(this);
        mBinding.lvContent.setLayoutManager(new LinearLayoutManager(this));
        mBinding.lvContent.setAdapter(mContentAdapter);

        mBinding.vgSwipe.setOnRefreshListener(this);
        mContentAdapter.setOnItemClickListener(this);

        return mBinding;
    }

    @Override
    public void onRefresh() {
        getPresenter().refreshList();
    }

    @Override
    public void onItemClick(int position, HomeItemViewModel item) {
        getPresenter().openPage(this, item.getClassObject());
    }

    public static class HomeAdapter extends BindAdapter<HomeItemViewModel, HomeListItemBinding> {

        public HomeAdapter(Context context) {
            super(context);
        }

        @Override
        public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            HomeListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_list_item, null, false);
            return new BindViewHolder(binding.getRoot());
        }
    }
}
