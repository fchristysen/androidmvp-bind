package org.greenfroyo.androidmvp_bind.app.chat;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;

import java.util.Calendar;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatPresenter extends BaseToolbarPresenter<ChatViewModel> {

    @Override
    public ChatViewModel onCreateViewModel() {
        ChatViewModel model = new ChatViewModel();
        ChatItemViewModel item = new ChatItemViewModel();
        item.isMine = true;
        item.mUsername = "Me";
        item.mText = "Hello John";
        item.mTime = Calendar.getInstance().getTimeInMillis();
        model.addChat(item);
        item = new ChatItemViewModel();
        item.isMine = false;
        item.mUsername = "John";
        item.mText = "Hi!!! How are you?";
        item.mTime = Calendar.getInstance().getTimeInMillis();
        model.addChat(item);
        return model;
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
