package org.greenfroyo.androidmvp_bind.app.chat;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;

import java.util.Calendar;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatPresenter extends BaseToolbarPresenter<ChatViewModel> {

    @Override
    public ChatViewModel onCreateViewModel() {
        return new ChatViewModel();
    }

    public void sendChat(String text){
        ChatItemViewModel item = new ChatItemViewModel();
        item.setMine(true);
        item.setUsername("Me");
        item.setText(text);
        item.setTime(Calendar.getInstance().getTimeInMillis());
        getViewModel().addChat(item);
        getViewModel().setInputText("");
    }
}
