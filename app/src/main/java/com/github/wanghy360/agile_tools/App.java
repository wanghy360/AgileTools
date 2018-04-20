package com.github.wanghy360.agile_tools;

import android.app.Application;
import com.github.wanghy360.base.network.NetStatusWatch;

/**
 * Created by wrongyuan on 2017/3/6
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetStatusWatch.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        NetStatusWatch.getInstance().clearAllListener(this);
    }
}
