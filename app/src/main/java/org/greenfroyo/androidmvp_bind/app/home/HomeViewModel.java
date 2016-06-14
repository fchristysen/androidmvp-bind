package org.greenfroyo.androidmvp_bind.app.home;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

import java.util.List;

/**
 * Created by fchristysen on 6/7/16.
 */

public class HomeViewModel extends BaseViewModel{
    public static final int STATE_SHOW = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;

    private int mPageState;
    private List<String> mContent;

    public int getPageState() {
        return mPageState;
    }

    public void setPageState(int pageState) {
        mPageState = pageState;
    }

    public List<String> getContent() {
        return mContent;
    }

    public void setContent(List<String> content) {
        mContent = content;
    }
}
