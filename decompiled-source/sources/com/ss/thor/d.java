package com.ss.thor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
/* compiled from: ThorUtils.java */
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static BatteryManager f18790a;

    /* renamed from: b  reason: collision with root package name */
    private static IntentFilter f18791b = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    private static BatteryManager a(Context context) {
        if (f18790a == null) {
            synchronized (d.class) {
                if (f18790a == null) {
                    if (Build.VERSION.SDK_INT < 21) {
                        return null;
                    }
                    f18790a = (BatteryManager) context.getSystemService("batterymanager");
                }
            }
        }
        return f18790a;
    }

    public static double getUseBattery(Context context) {
        return (getGalvanicNow(context) * 0.5d) / 3600.0d;
    }

    public static float getVoltage(Context context) {
        Intent registerReceiver;
        if (context == null || (registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) == null) {
            return -1.0f;
        }
        return registerReceiver.getIntExtra("voltage", -1) / 1000.0f;
    }

    public static float getGalvanicNow(Context context) {
        BatteryManager a2;
        if (Build.VERSION.SDK_INT < 21 || context == null || (a2 = a(context)) == null) {
            return -1.0f;
        }
        float longProperty = (float) a2.getLongProperty(2);
        if (longProperty >= -1.0E7f && longProperty <= 1.0E7f) {
            if (!e.isSihi() && !e.isSamsung()) {
                return (!e.isOppo() || longProperty > 10000.0f) ? longProperty / 1000.0f : longProperty;
            }
            if (longProperty < -10000.0f) {
                longProperty /= 1000.0f;
            }
            return -longProperty;
        }
        return -1.0f;
    }

    public static float getGalvanicAvg(Context context) {
        BatteryManager a2;
        if (Build.VERSION.SDK_INT < 21 || context == null || (a2 = a(context)) == null) {
            return -1.0f;
        }
        float longProperty = (float) a2.getLongProperty(3);
        if (!e.isSihi() && !e.isSamsung()) {
            return (!e.isOppo() || longProperty > 10000.0f) ? longProperty / 1000.0f : longProperty;
        }
        if (longProperty >= -1.0E7f && longProperty <= 1.0E7f) {
            if (longProperty < -10000.0f) {
                longProperty /= 1000.0f;
            }
            return -longProperty;
        }
        return -1.0f;
    }

    public static long getCapacity(Context context) {
        BatteryManager a2;
        if (Build.VERSION.SDK_INT < 21 || context == null || (a2 = a(context)) == null) {
            return -1L;
        }
        return a2.getLongProperty(4);
    }

    public static double getEnergy(Context context) {
        BatteryManager a2;
        if (Build.VERSION.SDK_INT < 21 || context == null || (a2 = a(context)) == null) {
            return -1.0d;
        }
        return (a2.getLongProperty(5) / 1.0E9d) / 1000.0d;
    }
}
