package org.greenfroyo.androidmvp_bind.app.chat;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.common.BindAdapter;
import org.greenfroyo.androidmvp_bind.app.common.delegate.AdapterDelegate;
import org.greenfroyo.androidmvp_bind.databinding.ChatMineBinding;
import org.greenfroyo.androidmvp_bind.databinding.HomeListItemBinding;

/**
 * Created by fchristysen on 8/15/16.
 */

public class ChatMineDelegateAdapter implements AdapterDelegate<ChatItemViewModel, BindAdapter.BindViewHolder> {

    @Override
    public boolean isForViewType(@NonNull ChatItemViewModel items, int position) {
        return items.isMine;
    }

    @NonNull
    @Override
    public BindAdapter.BindViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ChatMineBinding binding = DataBindingUtil.inflate(inflater, R.layout.chat_mine, null, false);
        return new BindAdapter.BindViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatItemViewModel items, int position, @NonNull BindAdapter.BindViewHolder holder) {

    }
}
