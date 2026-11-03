package com.bytedance.crash.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.crash.Global;
/* compiled from: BatteryTracker.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: b  reason: collision with root package name */
    private static volatile b f7179b;

    /* renamed from: a  reason: collision with root package name */
    private int f7180a;

    static b a() {
        if (f7179b == null) {
            synchronized (b.class) {
                if (f7179b == null) {
                    f7179b = new b();
                }
            }
        }
        return f7179b;
    }

    private void d() {
        Context c2 = Global.c();
        if (c2 == null) {
            return;
        }
        try {
            c2.registerReceiver(new BroadcastReceiver() { // from class: com.bytedance.crash.tracker.BatteryTracker$1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    try {
                        if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                            int intExtra = intent.getIntExtra("level", 0);
                            b.this.f7180a = (intExtra * 100) / intent.getIntExtra("scale", 100);
                        }
                    } catch (Exception unused) {
                    }
                }
            }, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (Exception unused) {
        }
    }

    public static int b() {
        return a().f7180a;
    }

    public static void c() {
        a().d();
    }
}
