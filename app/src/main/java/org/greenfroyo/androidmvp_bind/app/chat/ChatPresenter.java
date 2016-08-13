package org.greenfroyo.androidmvp_bind.app.chat;

import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarPresenter;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatPresenter extends BaseToolbarPresenter<ChatViewModel> {

    @Override
    public ChatViewModel onCreateViewModel() {
        return new ChatViewModel();
    }
}
