package com.github.wrongyuan.util_lib.network;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wanghy on 2017/3/6
 */
public class NetStatusWatch {
    private NetworkStatus currNetStatus = NetworkStatus.NETWORK_NONE;
    private List<OnNetStatusChangedListener> arrNetStatusListenr;
    private NetChangeBroadCastReceiver netChangeReciver;
    private static NetStatusWatch instance;

    public NetStatusWatch() {
        arrNetStatusListenr = new ArrayList<>();
    }

    public void regisiterListener(OnNetStatusChangedListener listener) {
        if (listener == null || arrNetStatusListenr.contains(listener)) {
            return;
        }

        arrNetStatusListenr.add(listener);
    }

    public void unRegisiterListener(OnNetStatusChangedListener listener) {
        if (listener == null) {
            return;
        }

        for (OnNetStatusChangedListener o : arrNetStatusListenr) {
            if (o.equals(listener)) {
                arrNetStatusListenr.remove(listener);
                break;
            }
        }
    }

    public void clearAllListener(Application application) {
        arrNetStatusListenr.clear();
        try {
            if (netChangeReciver != null) {
                netChangeReciver.setListener(null);
                application.unregisterReceiver(netChangeReciver);
                netChangeReciver = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notifyAllListener() {
        for (OnNetStatusChangedListener listener : arrNetStatusListenr) {
            if (listener != null) {
                listener.onNetStatusChanged(currNetStatus);
            }
        }
    }

    public interface OnNetStatusChangedListener {
        void onNetStatusChanged(NetworkStatus currNetStatus);
    }

    public static NetStatusWatch getInstance() {
        if (instance == null)
            instance = new NetStatusWatch();

        return instance;
    }

    public void init(Application application) {
        currNetStatus = NetUtil.getNetWorkState(application);
        if (netChangeReciver == null) {
            netChangeReciver = new NetChangeBroadCastReceiver();
            netChangeReciver.setListener(new NetChangeBroadCastReceiver.OnNetChangedListener() {
                @Override
                public void onNetChanged(NetworkStatus currNetStatus) {
                    NetStatusWatch.this.currNetStatus = currNetStatus;
                    notifyAllListener();
                }
            });
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            application.registerReceiver(netChangeReciver, intentFilter);
        }
    }

    public NetworkStatus getCurrNetStatus() {
        return currNetStatus;
    }
}
