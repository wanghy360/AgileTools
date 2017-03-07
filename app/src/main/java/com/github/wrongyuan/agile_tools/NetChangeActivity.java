package com.github.wrongyuan.agile_tools;

import android.os.Bundle;
import android.util.Log;

import com.github.wrongyuan.util_lib.network.NetStatusWatch;
import com.github.wrongyuan.util_lib.network.NetworkStatus;

public class NetChangeActivity extends BaseActivity implements NetStatusWatch.OnNetStatusChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_change);
        NetStatusWatch.getInstance().regisiterListener(this);
        Log.d(TAG,"Net status Start : "+NetStatusWatch.getInstance().getCurrNetStatus().name());
    }

    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {
        Log.d(TAG, "currNetStatus : " + currNetStatus.name());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStatusWatch.getInstance().unRegisiterListener(this);
    }
}
