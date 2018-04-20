package com.github.wanghy360.agile_tools;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.wanghy360.base.network.NetStatusWatch;
import com.github.wanghy360.base.network.NetworkStatus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NetChangeActivity extends SimpleListActivity implements NetStatusWatch.OnNetStatusChangedListener {
    private Unbinder unbinder;
    @BindView(R.id.list_net_status)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_change);
        initView();
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        initSimpleRecyclerView(recyclerView);
        NetStatusWatch.getInstance().regisiterListener(this);
        Log.d(TAG, "Net status Start : " + NetStatusWatch.getInstance().getCurrNetStatus().name());
    }

    @Override
    public void onNetStatusChanged(NetworkStatus currNetStatus) {
        Log.d(TAG, "currNetStatus : " + currNetStatus.name());
        simpleAdapter.addData(currNetStatus.name());
    }

    @OnClick(R.id.btn_clear_content)
    public void clearItems(){
        simpleAdapter.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStatusWatch.getInstance().unRegisiterListener(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
