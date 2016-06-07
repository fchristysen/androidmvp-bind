package org.sadalsuud.androidmvp_bind.app.home;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.sadalsuud.androidmvp_bind.R;
import org.sadalsuud.androidmvp_bind.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel> {

    //region Views
    @BindView(R.id.tv_header) TextView vHeader;
    @BindView(R.id.lv_content) ListView vContent;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onInitView() {
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onInitState() {
        vHeader.setText("Hello World!!!");
    }

    @Override
    protected void onInitListener() {

    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    public void onViewModelChanged(HomeViewModel viewModel) {

    }
}
