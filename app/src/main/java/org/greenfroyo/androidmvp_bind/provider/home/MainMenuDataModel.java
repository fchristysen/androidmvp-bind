package org.greenfroyo.androidmvp_bind.provider.home;

/**
 * Created by fchristysen on 7/1/16.
 */

public class MainMenuDataModel {
    private Class mPages;

    public MainMenuDataModel(Class pages) {
        mPages = pages;
    }

    public Class getPages() {
        return mPages;
    }

    public void setPages(Class pages) {
        mPages = pages;
    }
}
