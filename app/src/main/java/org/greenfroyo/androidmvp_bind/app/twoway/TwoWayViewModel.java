package org.greenfroyo.androidmvp_bind.app.twoway;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayViewModel extends BaseToolbarViewModel {
    public ObservableField<String> mText;

    public TwoWayViewModel(){
        this.mText = new ObservableField<>("Default Text");
    }
}
