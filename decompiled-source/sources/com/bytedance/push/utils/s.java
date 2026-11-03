package com.bytedance.push.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
/* compiled from: BatteryUtils.java */
/* loaded from: classes2.dex */
public class s {
    public static boolean a(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return registerReceiver != null && registerReceiver.getIntExtra("plugged", -1) == 2;
    }
}
