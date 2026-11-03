package com.bytedance.apm.battery.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.apm.battery.util.a;
/* JADX INFO: Access modifiers changed from: private */
/* loaded from: classes.dex */
public class BatteryUtils$PowerConnectionReceiver extends BroadcastReceiver {
    private BatteryUtils$PowerConnectionReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.ACTION_POWER_CONNECTED".equals(action)) {
            a.a(true);
            for (a.InterfaceC0015a interfaceC0015a : a.a()) {
                interfaceC0015a.b(a.b());
            }
        } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            a.a(false);
            for (a.InterfaceC0015a interfaceC0015a2 : a.a()) {
                interfaceC0015a2.b(a.b());
            }
        }
    }
}
