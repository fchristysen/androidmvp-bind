package org.greenfroyo.androidmvp_bind.app.chat;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;
import org.json.JSONArray;
import org.parceler.Parcel;
import org.parceler.ParcelClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by fchristysen on 8/12/16.
 */
@Parcel
@ParcelClass(JSONArray.class)
public class ChatViewModel extends BaseToolbarViewModel {
    protected JSONArray mJson;
    protected List<ChatItemViewModel> mChats;
    protected String mInputText;

    public ChatViewModel(){
        mChats = new ArrayList<>();
    }

    @Bindable
    public List<ChatItemViewModel> getChats() {
        return mChats;
    }

    public void setChats(List<ChatItemViewModel> chats) {
        mChats = chats;
        notifyPropertyChanged(BR.chats);
    }

    public void addChat(ChatItemViewModel item){
        mChats.add(item);
        notifyPropertyChanged(BR.chats);
    }

    @Bindable
    public String getInputText() {
        return mInputText;
    }

    public void setInputText(String inputText) {
        mInputText = inputText;
        notifyPropertyChanged(BR.inputText);
    }
}
