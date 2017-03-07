package com.github.wrongyuan.util_lib.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetChangeBroadCastReciver extends BroadcastReceiver {

    private OnNetChangedListener listener;

    public NetChangeBroadCastReciver() {
        super();
    }

    public interface OnNetChangedListener {
        void onNetChanged(NetworkStatus currNetStatus);
    }

    public void setListener(OnNetChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (listener != null) {
                listener.onNetChanged(NetUtil.getNetWorkState(context));
            }
        }
    }
}
