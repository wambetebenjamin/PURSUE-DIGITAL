package com.bytedance.apm.battery.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import java.util.ArrayList;
import java.util.List;
/* compiled from: BatteryUtils.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a */
    static BatteryManager f688a;

    /* renamed from: b */
    private static List<InterfaceC0015a> f689b = new ArrayList();

    /* renamed from: c */
    private static boolean f690c = false;

    /* renamed from: d */
    private static boolean f691d;

    /* renamed from: e */
    private static BatteryUtils$PowerConnectionReceiver f692e;

    /* compiled from: BatteryUtils.java */
    /* renamed from: com.bytedance.apm.battery.util.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0015a {
        void b(boolean z);
    }

    private static void a(Context context) {
        f688a = (BatteryManager) context.getSystemService("batterymanager");
    }

    public static long getCurrentNow(Context context) {
        a(context);
        return f688a.getLongProperty(2);
    }

    public static boolean isCharging(Context context) {
        int intExtra;
        if (f690c) {
            return f691d;
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return registerReceiver == null || (intExtra = registerReceiver.getIntExtra("status", -1)) == 2 || intExtra == 5;
    }

    public static boolean a(Context context, InterfaceC0015a interfaceC0015a) {
        if (interfaceC0015a == null) {
            throw new IllegalArgumentException("chargeListener can't be null");
        }
        f689b.add(interfaceC0015a);
        if (!f690c) {
            b(context);
            f690c = true;
        }
        return f691d;
    }

    public static void b(Context context, InterfaceC0015a interfaceC0015a) {
        if (interfaceC0015a == null) {
            return;
        }
        f689b.remove(interfaceC0015a);
        if (f689b.isEmpty()) {
            try {
                context.unregisterReceiver(f692e);
            } catch (Throwable unused) {
            }
            f690c = false;
        }
    }

    private static void b(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
            BatteryUtils$PowerConnectionReceiver batteryUtils$PowerConnectionReceiver = new BatteryUtils$PowerConnectionReceiver();
            f692e = batteryUtils$PowerConnectionReceiver;
            context.registerReceiver(batteryUtils$PowerConnectionReceiver, intentFilter);
            f691d = isCharging(context);
        } catch (Throwable unused) {
            f691d = true;
        }
    }
}
