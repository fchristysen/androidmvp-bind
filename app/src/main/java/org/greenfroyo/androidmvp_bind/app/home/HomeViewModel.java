package org.greenfroyo.androidmvp_bind.app.home;

import android.databinding.Bindable;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomeViewModel extends BaseViewModel {
    public static final int STATE_SHOW = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;

    public int mPageState;
    private List<HomeItemViewModel> mContent;

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
            return "Prototype";
        }else if(mPageState == STATE_LOADING){
            return  "Loading items...";
        }else if(mPageState == STATE_ERROR){
            return  "Error. Please try again.";
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

    public void addContent(HomeItemViewModel item){
        mContent.add(item);
        notifyPropertyChanged(BR.content);
    }

    public void clearContent() {
        mContent.clear();
    }
    //

}
