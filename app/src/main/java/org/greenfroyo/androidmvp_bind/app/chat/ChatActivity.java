package org.greenfroyo.androidmvp_bind.app.chat;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app._core.toolbar.BaseToolbarActivity;
import org.greenfroyo.androidmvp_bind.app.common.BindAdapter;
import org.greenfroyo.androidmvp_bind.app.common.delegate.DelegateManager;
import org.greenfroyo.androidmvp_bind.databinding.ChatActivityBinding;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

/**
 * Created by fchristysen on 8/12/16.
 */

public class ChatActivity extends BaseToolbarActivity<ChatPresenter, ChatViewModel> implements View.OnClickListener {

    private ChatActivityBinding mBinding;

    @Override
    public ChatPresenter createPresenter() {
        return new ChatPresenter();
    }

    @Override
    protected ViewDataBinding onInitView(ChatViewModel viewModel) {
        mBinding = setBindView(R.layout.chat_activity);
        mBinding.lvContent.setAdapter(new ChatAdapter(this));
        mBinding.lvContent.setLayoutManager(new LinearLayoutManager(this));
        mBinding.setViewModel(viewModel);
        mBinding.btnSend.setOnClickListener(this);
        return mBinding;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBinding.btnSend)){
            getPresenter().sendChat(mBinding.etText.getText().toString());
        }
    }

    public class ChatAdapter extends BindAdapter<ChatItemViewModel, BindAdapter.BindViewHolder>{
        private DelegateManager<ChatItemViewModel, BindViewHolder> mDelegateManager;

        public ChatAdapter(Context context) {
            super(context);
            mDelegateManager = new DelegateManager();
            mDelegateManager.addDelegate(new ChatMineDelegateAdapter());
            mDelegateManager.addDelegate(new ChatOthersDelegateAdapter());
        }

        @Override
        public int getItemViewType(int position) {
            return mDelegateManager.getItemViewType(getItem(position), position);
        }

        @Override
        public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return mDelegateManager.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(BindViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            mDelegateManager.onBindViewHolder(getItem(position), position, holder);
        }
    }
}
