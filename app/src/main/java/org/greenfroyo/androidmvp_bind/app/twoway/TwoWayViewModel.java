package org.greenfroyo.androidmvp_bind.app.twoway;

import android.databinding.ObservableField;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayViewModel extends BaseViewModel {
    private ObservableField<String> mText;

    public TwoWayViewModel(){
        this.mText = new ObservableField<>("Default Text");
    }

    public String getText() {
        return mText.get();
    }

    public void setText(String text) {
        mText.set(text);
    }
}
