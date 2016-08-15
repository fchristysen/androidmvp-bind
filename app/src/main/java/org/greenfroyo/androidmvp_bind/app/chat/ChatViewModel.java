package org.greenfroyo.androidmvp_bind.app.chat;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatViewModel extends BaseToolbarViewModel {
    protected List<ChatItemViewModel> mChats;
    protected String mInputText;

    public ChatViewModel(){
        mChats = new ArrayList<>();
        mock();
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

    public String getInputText() {
        return mInputText;
    }

    public void setInputText(String inputText) {
        mInputText = inputText;
    }

    private void mock(){
        ChatItemViewModel item = new ChatItemViewModel();
        item.isMine = true;
        item.mUsername = "Me";
        item.mText = "Hello John";
        item.mTime = Calendar.getInstance().getTimeInMillis();
        mChats.add(item);
        item = new ChatItemViewModel();
        item.isMine = false;
        item.mUsername = "John";
        item.mText = "Hi!!! How are you?";
        item.mTime = Calendar.getInstance().getTimeInMillis();
        mChats.add(item);
        notifyPropertyChanged(BR.chats);
    }
}
