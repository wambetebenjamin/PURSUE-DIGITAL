package com.bytedance.ttgame.sdk.module.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes8.dex */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private NetworkChangeManager networkChangeManager;

    /* loaded from: classes8.dex */
    public interface INetworkAvailable {
        default void isAvailable(boolean z) {
        }

        default void isWifi(boolean z) {
        }
    }

    public NetworkChangeReceiver(NetworkChangeManager networkChangeManager) {
        this.networkChangeManager = networkChangeManager;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnectedOrConnecting()) {
                NetworkChangeManager networkChangeManager = this.networkChangeManager;
                if (networkChangeManager != null) {
                    networkChangeManager.onWifiChanged(true);
                }
            } else {
                NetworkChangeManager networkChangeManager2 = this.networkChangeManager;
                if (networkChangeManager2 != null) {
                    networkChangeManager2.onWifiChanged(false);
                }
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                NetworkChangeManager networkChangeManager3 = this.networkChangeManager;
                if (networkChangeManager3 != null) {
                    networkChangeManager3.onNetworkChanged(true);
                    return;
                }
                return;
            }
            NetworkChangeManager networkChangeManager4 = this.networkChangeManager;
            if (networkChangeManager4 != null) {
                networkChangeManager4.onNetworkChanged(false);
            }
        }
    }
}
