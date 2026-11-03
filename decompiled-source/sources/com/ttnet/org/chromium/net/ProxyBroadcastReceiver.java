package com.ttnet.org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes9.dex */
final class ProxyBroadcastReceiver extends BroadcastReceiver {
    private final ProxyChangeListener mListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProxyBroadcastReceiver(ProxyChangeListener proxyChangeListener) {
        this.mListener = proxyChangeListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
            this.mListener.updateProxyConfigFromConnectivityManager(intent);
        }
    }
}
