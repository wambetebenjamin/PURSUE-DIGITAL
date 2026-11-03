package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFd1vSDK;
import com.bytedance.bdinstall.e;
/* loaded from: classes.dex */
public final class AFa1tSDK implements AFd1vSDK {
    private static IntentFilter valueOf = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    @Override // com.appsflyer.internal.AFd1vSDK
    public final AFd1vSDK.AFa1ySDK AFInAppEventParameterName(Context context) {
        String str = null;
        float f2 = 0.0f;
        try {
            Intent registerReceiver = context.registerReceiver(null, valueOf);
            if (registerReceiver != null) {
                if (2 == registerReceiver.getIntExtra("status", -1)) {
                    int intExtra = registerReceiver.getIntExtra("plugged", -1);
                    str = intExtra != 1 ? intExtra != 2 ? intExtra != 4 ? "other" : "wireless" : "usb" : e.aE;
                } else {
                    str = "no";
                }
                int intExtra2 = registerReceiver.getIntExtra("level", -1);
                int intExtra3 = registerReceiver.getIntExtra("scale", -1);
                if (-1 != intExtra2 && -1 != intExtra3) {
                    f2 = (intExtra2 * 100.0f) / intExtra3;
                }
            }
        } catch (Throwable th) {
            AFLogger.afErrorLogForExcManagerOnly("Device that failed to register receiver", th);
        }
        return new AFd1vSDK.AFa1ySDK(f2, str);
    }
}
