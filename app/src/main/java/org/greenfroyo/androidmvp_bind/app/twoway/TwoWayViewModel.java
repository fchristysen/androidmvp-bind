package org.greenfroyo.androidmvp_bind.app.twoway;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;

/**
 * Created by fchristysen on 6/23/16.
 */

public class TwoWayViewModel extends BaseViewModel {
    public ObservableField<String> mText;
    public TextWatcher mWatcher;

    public TwoWayViewModel(){
        this.mText = new ObservableField<>("Default Text");
        this.mWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(mText.get())){
                    mText.set(s.toString());
                }
            }
        };
    }
}
