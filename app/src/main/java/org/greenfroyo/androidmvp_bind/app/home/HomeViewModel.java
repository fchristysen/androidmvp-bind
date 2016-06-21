package org.greenfroyo.androidmvp_bind.app.home;

import android.databinding.Bindable;
import android.databinding.ObservableField;

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
    private ObservableField<List<HomeItemViewModel>> mContent;

    public HomeViewModel(){
        mContent = new ObservableField<>(new ArrayList<>());
    }

    //region direct_accessor
    public int getPageState() {
        return mPageState;
    }

    public List<HomeItemViewModel> getContent() {
        return mContent.get();
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
    //endregion

    //region mutator
    public void setPageState(int pageState) {
        mPageState = pageState;
        notifyPropertyChanged(BR.pageTitle);
    }

    public void addContent(HomeItemViewModel item){
        mContent.get().add(item);
        notifyPropertyChanged(BR.item);
        notifyPropertyChanged(BR.home);
    }

    public void clearContent() {
        mContent.get().clear();
    }

    public void setContentListener(OnPropertyChangedCallback callback){
        mContent.addOnPropertyChangedCallback(callback);
    }
    //

}
