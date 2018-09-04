package com.github.wanghy360.base.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;

public class NetChangeBroadCastReceiver extends BroadcastReceiver {

    private OnNetChangedListener listener;

    public NetChangeBroadCastReceiver() {
        super();
    }

    private Handler handler;
    private Runnable connectRunable;

    private NetworkStatus networkStatus;

    public interface OnNetChangedListener {
        void onNetChanged(NetworkStatus currNetStatus);
    }

    public void setListener(OnNetChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //wifi由开而关，先不通知，5000ms后检测到依然无网络，此时通知。原因是:移动网络打开时，BroadCastReciver会接到2次消息，第一次检测网络是不通的，其实只是在切换中，第二次检测网络为移动网络了。
            if (intent.getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE, -1) == ConnectivityManager.TYPE_WIFI && intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
                if (handler == null)
                    handler = new Handler(Looper.getMainLooper());
                if (connectRunable == null) {
                    connectRunable = new Runnable() {
                        @Override
                        public void run() {
                            networkStatus = NetUtil.getNetWorkState(context);
                            if (listener != null) {
                                listener.onNetChanged(networkStatus);
                            }
                        }
                    };
                }else {
                    handler.removeCallbacks(connectRunable);
                }
                handler.postDelayed(connectRunable, 5000);
            } else {
                if (handler != null && connectRunable != null) {
                    handler.removeCallbacks(connectRunable);
                }
                NetworkStatus networkStatus = NetUtil.getNetWorkState(context);
                if (this.networkStatus == networkStatus) {
                    return;
                }
                this.networkStatus = networkStatus;
                if (listener != null) {
                    listener.onNetChanged(NetUtil.getNetWorkState(context));
                }
            }
        }
    }
}