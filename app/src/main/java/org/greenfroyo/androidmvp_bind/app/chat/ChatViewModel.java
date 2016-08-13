package org.greenfroyo.androidmvp_bind.app.chat;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarViewModel;

import java.util.List;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatViewModel extends BaseToolbarViewModel {
    protected List<ChatItemViewModel> mChats;
    protected String mInputText;

    public List<ChatItemViewModel> getChats() {
        return mChats;
    }

    public void setChats(List<ChatItemViewModel> chats) {
        mChats = chats;
    }

    public String getInputText() {
        return mInputText;
    }

    public void setInputText(String inputText) {
        mInputText = inputText;
    }
}
