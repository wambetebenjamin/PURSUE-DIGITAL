package com.bytedance.bdinstall.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.common.utility.l;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: NetworkStatusProvider.java */
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4058a = "NetworkStatusProvider ";

    /* renamed from: b  reason: collision with root package name */
    private static volatile BroadcastReceiver f4059b;

    /* renamed from: d  reason: collision with root package name */
    private static volatile m f4060d;

    /* renamed from: e  reason: collision with root package name */
    private static volatile l.b f4061e = l.b.MOBILE;

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicInteger f4062f = new AtomicInteger(0);

    /* renamed from: c  reason: collision with root package name */
    private Context f4063c;

    public static m a(Context context) {
        if (f4060d == null) {
            synchronized (m.class) {
                if (f4060d == null) {
                    f4060d = new m(context);
                }
            }
        }
        return f4060d;
    }

    public static m a() {
        return f4060d;
    }

    private m(Context context) {
        this.f4063c = context;
        f();
    }

    public static void b() {
        f4062f.incrementAndGet();
        com.bytedance.bdinstall.u.a("NetworkStatusProvider increment reference count");
    }

    private void f() {
        com.bytedance.bdinstall.u.a("NetworkStatusProvider init monitor...");
        f4061e = com.bytedance.common.utility.l.getNetworkType(this.f4063c);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkChangeManager.ACTION);
        f4059b = new BroadcastReceiver() { // from class: com.bytedance.bdinstall.util.NetworkStatusProvider$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Context context2;
                l.b bVar;
                context2 = m.this.f4063c;
                l.b unused = m.f4061e = com.bytedance.common.utility.l.getNetworkType(context2);
                StringBuilder append = new StringBuilder().append("NetworkStatusProvider init broadcast receive that network type is ");
                bVar = m.f4061e;
                com.bytedance.bdinstall.u.a(append.append(bVar.name()).toString());
            }
        };
        this.f4063c.registerReceiver(f4059b, intentFilter);
    }

    public l.b c() {
        return f4061e;
    }

    public synchronized void d() {
        AtomicInteger atomicInteger = f4062f;
        if (atomicInteger.get() == 1 && f4059b != null) {
            this.f4063c.unregisterReceiver(f4059b);
            f4059b = null;
            atomicInteger.decrementAndGet();
            com.bytedance.bdinstall.u.a("NetworkStatusProvider stop monitor");
        } else if (atomicInteger.get() > 0) {
            atomicInteger.decrementAndGet();
            com.bytedance.bdinstall.u.a("NetworkStatusProvider decrement reference count");
        }
    }
}
