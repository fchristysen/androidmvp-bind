package org.greenfroyo.androidmvp_bind.app.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel> {

    //region Views
    @BindView(R.id.tv_header) protected TextView vHeader;
    @BindView(R.id.lv_content) protected ListView vContent;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onInitView() {
        super.onInitView();
        setContentView(R.layout.home_activity);
    }

    @Override
    public void onViewModelChanged(HomeViewModel viewModel) {
        if(viewModel.getPageState() == HomeViewModel.STATE_SHOW) {
            vHeader.setText("Prototype");
            vContent.setVisibility(View.VISIBLE);
            vContent.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, viewModel.getContent()));
        }else if(viewModel.getPageState() == HomeViewModel.STATE_LOADING) {
            vHeader.setText("Loading items...");
            vContent.setVisibility(View.VISIBLE);
            vContent.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, viewModel.getContent()));
        }else if(viewModel.getPageState() == HomeViewModel.STATE_ERROR) {
            vHeader.setText("Error. Please try again...");
            vContent.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onInitListener() {
        super.onInitListener();
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }



}
