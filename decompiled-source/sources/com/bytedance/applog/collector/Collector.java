package com.bytedance.applog.collector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.applog.log.o;
/* loaded from: classes.dex */
public class Collector extends BroadcastReceiver {
    public static final String KEY_DATA = "K_DATA";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String[] stringArrayExtra = intent.getStringArrayExtra(KEY_DATA);
        if (stringArrayExtra != null && stringArrayExtra.length > 0) {
            com.bytedance.applog.a.a(stringArrayExtra);
        } else {
            o.a().e("Collector onReceive empty", new Object[0]);
        }
    }
}
