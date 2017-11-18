package org.greenfroyo.androidmvp_bind.app.home;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.MVPBApp;
import org.greenfroyo.androidmvp_bind.app._core.error.BaseErrorViewModel;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fchristysen on 6/7/16.
 */

@Parcel
public class HomeViewModel extends BaseErrorViewModel {
    public static final int STATE_SHOW = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;

    protected int mPageState;
    protected List<HomeItemViewModel> mContent;

    public HomeViewModel(){
        mPageState = STATE_SHOW;
        mContent = new ArrayList<>();
    }

    //region direct_accessor
    public int getPageState() {
        return mPageState;
    }

    @Bindable
    public List<HomeItemViewModel> getContent() {
        return mContent;
    }
    //region end

    //region indirect_accessor
    @Bindable
    public String getPageTitle() {
        if(mPageState == STATE_SHOW){
            return MVPBApp.resources().getString(R.string.home_title_showing);
        }else if(mPageState == STATE_LOADING){
            return MVPBApp.resources().getString(R.string.home_title_loading);
        }else if(mPageState == STATE_ERROR){
            return MVPBApp.resources().getString(R.string.home_title_error);
        }
        return "";
    }

    @Bindable
    public boolean isRefreshing(){
        if(mPageState == STATE_LOADING)
            return true;
        else
            return false;
    }
    //endregion

    //region mutator
    public void setPageState(int pageState) {
        mPageState = pageState;
        notifyPropertyChanged(BR.pageTitle);
        notifyPropertyChanged(BR.refreshing);
    }

    public void setContent(List<HomeItemViewModel> content) {
        mContent = content;
    }

    public void addContent(HomeItemViewModel item){
        mContent.add(item);
        notifyPropertyChanged(BR.content);
    }

    public void clearContent() {
        mContent.clear();
    }
    //

}
