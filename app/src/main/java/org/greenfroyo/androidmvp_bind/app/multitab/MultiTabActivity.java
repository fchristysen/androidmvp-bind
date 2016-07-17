package org.greenfroyo.androidmvp_bind.app.multitab;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.BaseActivity;
import org.greenfroyo.androidmvp_bind.app.multitab.lorem.LoremFragment;
import org.greenfroyo.androidmvp_bind.databinding.MultitabActivityBinding;

/**
 * Created by fchristysen on 6/30/16.
 */

public class MultiTabActivity extends BaseActivity<MultiTabPresenter, MultiTabViewModel> {

    private static final Class<Fragment>[] mPages = new Class[]{
            LoremFragment.class
            , LoremFragment.class
            , LoremFragment.class
            , LoremFragment.class
    };

    private MultitabActivityBinding mBinding;

    @Override
    public MultiTabPresenter createPresenter() {
        return new MultiTabPresenter();
    }

    @Override
    protected ViewDataBinding onInitView(MultiTabViewModel viewModel) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.multitab_activity);
        mBinding.setViewModel(viewModel);
        viewModel.setAdapter(new MultiTabAdapter(getSupportFragmentManager(), mPages));
        return mBinding;
    }

    protected class MultiTabAdapter extends FragmentStatePagerAdapter{

        private Class<Fragment>[] mPages = new Class[]{};

        public MultiTabAdapter(FragmentManager fm, Class<Fragment>[] pages) {
            super(fm);
            this.mPages = pages;
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return mPages[position].newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int getCount() {
            return mPages.length;
        }

    }
}
