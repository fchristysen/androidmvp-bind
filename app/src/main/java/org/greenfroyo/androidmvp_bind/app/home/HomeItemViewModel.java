package org.greenfroyo.androidmvp_bind.app.home;

/**
 * Created by fchristysen on 6/21/16.
 */

public class HomeItemViewModel {
    private String mText;

    public HomeItemViewModel(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
