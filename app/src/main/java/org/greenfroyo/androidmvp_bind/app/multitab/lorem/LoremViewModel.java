package org.greenfroyo.androidmvp_bind.app.multitab.lorem;

import org.greenfroyo.androidmvp_bind.BR;
import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.App;
import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

/**
 * Created by fchristysen on 6/30/16.
 */

public class LoremViewModel extends BaseViewModel{

    private String mText;

    public LoremViewModel() {
        mText = App.resources().getString(R.string.placeholder_page_long);
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        if(mText.equals(text)) {
            return;
        }
        mText = text;
        notifyPropertyChanged(BR.text);
    }
}
