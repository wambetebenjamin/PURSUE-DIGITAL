package com.bytedance.common.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.bytedance.common.utility.l;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
/* compiled from: AppLogNetworkStatusMonitor.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: e  reason: collision with root package name */
    static final String f5177e = "NetworkStatusMonitor";

    /* renamed from: a  reason: collision with root package name */
    final Context f5178a;

    /* renamed from: c  reason: collision with root package name */
    BroadcastReceiver f5180c;

    /* renamed from: b  reason: collision with root package name */
    boolean f5179b = false;

    /* renamed from: d  reason: collision with root package name */
    l.b f5181d = l.b.MOBILE;

    public a(Context context) {
        this.f5178a = context;
        a();
    }

    private void a() {
        if (!this.f5179b) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(NetworkChangeManager.ACTION);
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.bytedance.common.utility.AppLogNetworkStatusMonitor$1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(NetworkChangeManager.ACTION)) {
                        try {
                            a aVar = a.this;
                            aVar.f5181d = l.getNetworkType(aVar.f5178a);
                        } catch (Exception e2) {
                            Log.w("NetworkStatusMonitor", "receive connectivity exception: " + e2);
                        }
                    }
                }
            };
            this.f5180c = broadcastReceiver;
            this.f5179b = true;
            try {
                this.f5178a.registerReceiver(broadcastReceiver, intentFilter);
            } catch (Throwable unused) {
            }
        }
        this.f5181d = l.getNetworkType(this.f5178a);
    }

    private void b() {
        if (this.f5179b) {
            this.f5179b = false;
            this.f5178a.unregisterReceiver(this.f5180c);
            this.f5180c = null;
        }
    }

    public void onResume() {
        a();
    }

    public void onPause() {
        b();
    }

    public void onDestroy() {
        b();
    }

    public l.b getNetworkType() {
        return this.f5181d;
    }

    public boolean isWifiOn() {
        return l.b.WIFI == this.f5181d;
    }

    public boolean isNetworkOn() {
        return l.b.NONE != this.f5181d;
    }
}
