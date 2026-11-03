package com.bytedance.common.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
/* loaded from: classes2.dex */
public class NetworkConnectChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (NetworkChangeManager.ACTION.equals(intent.getAction()) || "android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction()) || "android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
            l.a(l.getNetworkType(context));
        }
    }
}
