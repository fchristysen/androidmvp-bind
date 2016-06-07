package org.greenfroyo.androidmvp_bind.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel> {

    @Nullable @InjectExtra int id;
    @Nullable @InjectExtra String message;

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
        Dart.inject(this);
        vHeader.setText("Prototype");
    }

    @Override
    protected void onInitListener() {
        vHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Henson.with(HomeActivity.this)
                        .gotoHomeActivity().id(3).build();
                startActivity(intent);
            }
        });
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    public void onViewModelChanged(HomeViewModel viewModel) {

    }
}
