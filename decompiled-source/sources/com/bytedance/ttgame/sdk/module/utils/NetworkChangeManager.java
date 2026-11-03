package com.bytedance.ttgame.sdk.module.utils;

import android.content.Context;
import android.content.IntentFilter;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeReceiver;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import timber.log.Timber;
/* loaded from: classes8.dex */
public class NetworkChangeManager {
    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static NetworkChangeManager sInstance;
    private final List<NetworkChangeReceiver.INetworkAvailable> list = new CopyOnWriteArrayList();
    private NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(this);

    public static NetworkChangeManager getInstance() {
        if (sInstance == null) {
            synchronized (NetworkChangeManager.class) {
                if (sInstance == null) {
                    sInstance = new NetworkChangeManager();
                }
            }
        }
        return sInstance;
    }

    private NetworkChangeManager() {
    }

    public synchronized void register(Context context, NetworkChangeReceiver.INetworkAvailable iNetworkAvailable) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        this.list.add(iNetworkAvailable);
        if (context != null) {
            context.registerReceiver(this.networkChangeReceiver, intentFilter);
            Timber.tag("gsdk_cloud").d("注册网络广播监听器", new Object[0]);
        }
    }

    public synchronized void unRegisterNetworkChangeReceiver(Context context, NetworkChangeReceiver.INetworkAvailable iNetworkAvailable) {
        if (context != null) {
            NetworkChangeReceiver networkChangeReceiver = this.networkChangeReceiver;
            if (networkChangeReceiver != null) {
                context.unregisterReceiver(networkChangeReceiver);
                this.networkChangeReceiver = null;
                Timber.tag("gsdk_cloud").d("网络广播监听器unRegist", new Object[0]);
            }
        }
        if (this.list.contains(iNetworkAvailable)) {
            this.list.remove(iNetworkAvailable);
        }
    }

    public void onNetworkChanged(boolean z) {
        for (NetworkChangeReceiver.INetworkAvailable iNetworkAvailable : this.list) {
            iNetworkAvailable.isAvailable(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onWifiChanged(boolean z) {
        for (NetworkChangeReceiver.INetworkAvailable iNetworkAvailable : this.list) {
            iNetworkAvailable.isWifi(z);
        }
    }
}
