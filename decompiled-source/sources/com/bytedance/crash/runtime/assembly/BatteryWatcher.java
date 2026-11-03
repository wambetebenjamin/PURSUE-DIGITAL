package com.bytedance.crash.runtime.assembly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.crash.runtime.n;
/* JADX INFO: Access modifiers changed from: package-private */
@Deprecated
/* loaded from: classes2.dex */
public class BatteryWatcher {

    /* renamed from: a  reason: collision with root package name */
    private int f7033a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatteryWatcher(final Context context) {
        n.getDefaultHandler().post(new Runnable() { // from class: com.bytedance.crash.runtime.assembly.BatteryWatcher.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BatteryWatcher.this.a(context);
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        context.registerReceiver(new BatteryBroadcastReceiver(), new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public int getBatterLevel() {
        return this.f7033a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        private BatteryBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                    BatteryWatcher.this.f7033a = (int) ((intent.getIntExtra("level", 0) * 100.0f) / intent.getIntExtra("scale", 100));
                }
            } catch (Throwable unused) {
            }
        }
    }
}
