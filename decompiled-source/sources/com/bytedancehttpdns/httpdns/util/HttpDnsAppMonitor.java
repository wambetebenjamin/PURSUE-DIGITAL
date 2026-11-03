package com.bytedancehttpdns.httpdns.util;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import com.bytedancehttpdns.httpdns.HttpDns;
import com.bytedancehttpdns.httpdns.util.LifeCycleMonitor;
/* loaded from: classes8.dex */
public class HttpDnsAppMonitor implements LifeCycleMonitor.AppStateListener {
    private static final String TAG = "veHttpDns";
    private LifeCycleMonitor mLifeCycleMonitor = new LifeCycleMonitor();
    private BroadcastReceiver mNetworkStateReceiver = new BroadcastReceiver() { // from class: com.bytedancehttpdns.httpdns.util.HttpDnsAppMonitor.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast() && NetworkChangeManager.ACTION.equals(intent.getAction())) {
                HttpDns.getService().onNetworkChanged();
            }
        }
    };

    @Override // com.bytedancehttpdns.httpdns.util.LifeCycleMonitor.AppStateListener
    public void onEnterToBackground() {
    }

    @Override // com.bytedancehttpdns.httpdns.util.LifeCycleMonitor.AppStateListener
    public void onEnterToForeground() {
        HttpDns.getService().onResume();
    }

    public void registerAppMonitorListener(Context context) {
        registerNetworkStateChanged(context);
        registerForeBackgroundCallback(context);
    }

    private void registerNetworkStateChanged(Context context) {
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(NetworkChangeManager.ACTION);
            context.registerReceiver(this.mNetworkStateReceiver, intentFilter);
        }
    }

    private void registerForeBackgroundCallback(Context context) {
        Application application;
        if (!(context instanceof Application) || (application = (Application) context) == null) {
            return;
        }
        this.mLifeCycleMonitor.setAppStateChangedListener(this);
        application.registerActivityLifecycleCallbacks(this.mLifeCycleMonitor);
    }
}
