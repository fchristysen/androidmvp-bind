package org.greenfroyo.androidmvp_bind.provider.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import org.greenfroyo.androidmvp_bind.app.MVPBApp;

/**
 * Created by fchristysen on 8/1/16.
 */

public class LocalBroadcastBus {

    public static final String INTENT_BUNDLE = "intent_bundle";

    private static LocalBroadcastBus sInstance;

    public static LocalBroadcastBus get(){
        if(sInstance==null){
            sInstance = new LocalBroadcastBus(MVPBApp.context());
        }
        return sInstance;
    }

    private Context mContext;

    private LocalBroadcastBus(Context context){
        this.mContext = context;
    }

    public void send(String action){
        send(action, null);
    }

    public void send(String action, Bundle bundle){
        Intent intent = new Intent(action);
        if(bundle!=null){
            intent.putExtra(INTENT_BUNDLE, bundle);
        }
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    public void register(String action, BroadcastReceiver receiver){
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, new IntentFilter(action));
    }

    public void unregister(BroadcastReceiver receiver){
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver);
    }
}
