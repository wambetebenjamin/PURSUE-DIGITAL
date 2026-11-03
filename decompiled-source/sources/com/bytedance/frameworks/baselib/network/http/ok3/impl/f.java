package com.bytedance.frameworks.baselib.network.http.ok3.impl;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
/* compiled from: OkHttp3AppMonitor.java */
/* loaded from: classes2.dex */
public class f implements e.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7979a = "f";

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e f7980b = new com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e();

    /* renamed from: c  reason: collision with root package name */
    private BroadcastReceiver f7981c = new BroadcastReceiver() { // from class: com.bytedance.frameworks.baselib.network.http.ok3.impl.OkHttp3AppMonitor$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast() && NetworkChangeManager.ACTION.equals(intent.getAction())) {
                com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.e.a().j();
            }
        }
    };

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e.a
    public void b() {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.util.e.a
    public void a() {
        com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.e.a().i();
    }

    public void a(Context context) {
        b(context);
        c(context);
    }

    private void b(Context context) {
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(NetworkChangeManager.ACTION);
            context.registerReceiver(this.f7981c, intentFilter);
            if (Logger.debug()) {
                Logger.d(f7979a, "register network state changed completed");
            }
        }
    }

    private void c(Context context) {
        Application application;
        if (Logger.debug()) {
            Logger.d(f7979a, "register activity forground and background switch Callback");
        }
        if (!(context instanceof Application) || (application = (Application) context) == null) {
            return;
        }
        this.f7980b.a(this);
        application.registerActivityLifecycleCallbacks(this.f7980b);
    }
}
