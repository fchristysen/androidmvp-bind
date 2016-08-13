package org.greenfroyo.androidmvp_bind.app.chat;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.app.common.BindAdapter;
import org.greenfroyo.androidmvp_bind.databinding.ChatActivityBinding;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatActivity extends BaseToolbarActivity<ChatPresenter, ChatViewModel> {

    private ChatActivityBinding mBinding;

    @Override
    public ChatPresenter createPresenter() {
        return new ChatPresenter();
    }

    @Override
    protected ViewDataBinding onInitView(ChatViewModel viewModel) {
        mBinding = setBindView(R.layout.chat_activity);
        mBinding.setViewModel(viewModel);
//        mBinding.lvContent.setAdapter();
        return null;
    }

    public class ChatAdapter extends BindAdapter<ChatItemViewModel>{
        public ChatAdapter(Context context) {
            super(context);
        }

        @Override
        public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            HomeListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_list_item, null, false);
            return new BindViewHolder(binding.getRoot());
        }
    }
}
