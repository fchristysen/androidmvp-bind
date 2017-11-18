package org.greenfroyo.androidmvp_bind.app.chat;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.app._core.BaseViewModel;
import org.parceler.Parcel;

/**
 * Created by fchristysen on 8/12/16.
 */
@Parcel
public class ChatItemViewModel extends BaseViewModel {
    protected boolean isMine;
    protected String mUsername;
    protected String mText;
    protected long mTime;

    @Bindable
    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
        notifyPropertyChanged(BR.mine);
    }

    @Bindable
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        notifyPropertyChanged(BR.text);
    }

    @Bindable
    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
        notifyPropertyChanged(BR.time);
    }
}
